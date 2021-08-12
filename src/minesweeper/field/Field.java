package minesweeper.field;

import minesweeper.cell.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 * Class representing a minesweeper game field.
 */
public class Field implements IField {
    /**
     * Dimension of the field (square shape).
     */
    private static final int side = 9;

    /**
     * The cells of the field, to be accessed by lineal value of corresponding coordinates.
     */
    private final ICell[] cells = new Cell[side * side];

    /**
     * Lineal values of the cells coordinates which contain mines.
     */
    private int[] indexOfMines;

    /**
     * Total number of mines in the field.
     */
    private final int numberOfMines;

    /**
     * Flag value: true if the player has not yet interacted with the field.
     */
    private boolean pristine = true;

    {
        for (int i = 0; i < side * side; i++) {
            ICoordinates coordinates = new Coordinates(i);
            cells[i] = new Cell(coordinates);
        }
    }

    /**
     * Sole constructor.
     *
     * @param   numberOfMines - number of mines for the field
     */
    public Field(int numberOfMines) {
        this.numberOfMines = numberOfMines;
    }

    /**
     * Given a cell with mines, update CellValue of surrounding cells, by adding 1 to numeric value.
     * <p>
     * For example if the cell (1, 1) is given, and the cell (1, 0) is of value 4, it shall be updated to 5.
     *
     * @param number - the lineal value of coordinates
     */
    private void updateCellNumber(int number) {
        ICell cell = cells[number];
        if (!cell.isMine()) {
            CellValue newValue = cell.getValue().getNext();
            cell.setValue(newValue);
        }
    }

    /**
     * Place field mines. The total number of mines is {@link #numberOfMines}. This function accept a coordinate that
     * shall be kept without mines.
     *
     * @param      safeCoordinates - coordinates (ICoordinates) of cell that must be left without mines.
     * @throws     IllegalStateException if field is not pristine. This function should only be called before the any
     *             cell has been explored.
     */
    private void placeMines(ICoordinates safeCoordinates) {
        if (!pristine) {
            throw new IllegalStateException("Cannot place mines when field is no longer pristine");
        }

        indexOfMines = new Random()
                .ints(0, side * side)
                .filter(x -> safeCoordinates.getLineal() != x)
                .distinct()
                .limit(numberOfMines)
                .sorted()
                .toArray();

        for (int index : indexOfMines) {
            List<Integer> toUpdate = Coordinates.getSurrounding(index);
            for (Integer i : toUpdate) {
                updateCellNumber(i);
            }
            cells[index].setValue(CellValue.MINE);
        }
    }

    @Override
    public String toString() {
        String newLine = System.getProperty("line.separator");

        StringBuilder stringBuilder = new StringBuilder();

        // header
        stringBuilder.append(" |123456789|")
                .append(newLine)
                .append("-|---------|")
                .append(newLine);

        // body
        for (int i = 0; i < side; i++) {
            stringBuilder.append(i + 1).append("|");
            for (int j = 0; j < side; j++) {
                int index = j * side + i;
                stringBuilder.append(cells[index]);

            }
            stringBuilder.append("|")
                    .append(newLine);
        }

        // footer
        stringBuilder.append("-|---------|");

        return stringBuilder.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toggleMarkedCell(ICoordinates coordinates) {
        if (pristine) {
            placeMines(coordinates);
        }
        pristine = false;
        for (ICell cell : cells) {
            if (cell.getCoordinates().equals(coordinates)) {
                return cell.mark();
            }
        }
        throw new IllegalArgumentException("Coordinates not found: " + coordinates);
    }

    /**
     * Given a coordinates (ICoordinates) return corresponding field cell.
     *
     * @param   coordinates - the coordinate of cell to be fetched.
     * @return  ICell - the corresponding cell.
     */
    private ICell getCell(ICoordinates coordinates) {
        return cells[coordinates.getLineal()];
    }

    /**
     * Explore given cell. If no adjacent cell is a mine, calls itself with each of these cell.
     *
     * @param   coordinates - the coordinates (ICoordinates) of the cell to be explored
     * @param   first       - whether the cell to be explored was chosen by player, and not explored automatically.
     * @return  boolean - the result of first exploration: true if action is performed, false if the action is not
     *          allowed, because cell already explored.
     */
    private boolean exploreWithSurrounding(ICoordinates coordinates, boolean first) {
        List<Integer> toUpdate = Coordinates.getSurrounding(coordinates);
        boolean noneIsMine = toUpdate.stream().allMatch(lineal -> cells[lineal].getValue() != CellValue.MINE);
        ICell currentCell = getCell(coordinates);
        boolean result = false;
        if (first || (!currentCell.isMine() && !currentCell.isExplored()) && !currentCell.isMarked()) {
            result = currentCell.explore();
            if (noneIsMine) {
                toUpdate.forEach(lineal -> exploreWithSurrounding(new Coordinates(lineal), false));
            }
        }
        if (!first && currentCell.isMarked() && !currentCell.isMine()) {
            currentCell.mark();
            currentCell.explore();
            if (noneIsMine) {
                toUpdate.forEach(lineal -> exploreWithSurrounding(new Coordinates(lineal), false));
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * If the player explore for the first time, place mines into the field, but the first cell explored cannot be a
     * mine.
     */
    @Override
    public boolean explore(ICoordinates coordinates) {
        if (pristine) {
            placeMines(coordinates);
        }
        pristine = false;
        return exploreWithSurrounding(coordinates, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldStatus getFieldStatus() {
        // If all and only the real mine are marked by the player, he won
        int markedTotal = Arrays.stream(cells).mapToInt(a -> a.getFaceValue() == CellFaceValue.MARKED ? 1 : 0).sum();
        boolean allMinesMarked = Arrays.stream(indexOfMines).allMatch(x -> cells[x].isMarked());
        if (markedTotal == numberOfMines && allMinesMarked) {
            return FieldStatus.WIN;
        }

        // If at least one discovered field is MINE, player lost
        boolean oneIsMine = Arrays.stream(cells).anyMatch(cell -> cell.getFaceValue() == CellFaceValue.MINE);
        if (oneIsMine) {
            return FieldStatus.LOSS;
        }

        // the game continue
        return FieldStatus.PENDING;
    }

}
