package minesweeper.cell;

/**
 * A class presenting a single cell in minesweeper game field.
 */
public class Cell implements ICell {
    private boolean explored = false;
    private boolean marked = false;
    private CellValue value;
    private final ICoordinates coordinates;

    /**
     * Sole constructor
     *
     * @param coordinates - the coordinates of the cell
     */
    public Cell(ICoordinates coordinates) {
        this.coordinates = coordinates;
        this.value = CellValue.SAFE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CellValue getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ICoordinates getCoordinates() {
        return coordinates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CellFaceValue getFaceValue() {
        if (explored && isMine()) {
            return CellFaceValue.MINE;
        }

        if (explored && value.isNumber()) {
            return CellFaceValue.fromValue(value);
        }

        if (marked) {
            return CellFaceValue.MARKED;
        }

        if (!explored) {
            return CellFaceValue.BLANK;
        }

        return CellFaceValue.EXPLORED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMarked() {
        return getFaceValue() == CellFaceValue.MARKED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean mark() {
        if (getFaceValue().isNumber()) {
            return false;
        }

        marked = !marked;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean explore() {
        if (explored) {
            return false;
        }

        explored = true;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMine() {
        return value == CellValue.MINE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExplored() {
        return explored;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(CellValue value) {
        if (this.value.getNext() != value && value != CellValue.MINE) {
            String error = String.format("Cannot set cell value to %s from %s.", value, this.value);
            throw new IllegalStateException(error);
        }
        if (value == CellValue.SAFE) {
            throw new IllegalStateException("Cannot set cell value to SAFE.");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return getFaceValue().toString();
    }

}
