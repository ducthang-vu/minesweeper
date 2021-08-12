package minesweeper.field;

import minesweeper.cell.ICoordinates;

/**
 * Interface for a game field for minesweeper.
 */
public interface IField {
    /**
     * Explore a cell of the field.
     *
     * @param   coordinates - the coordinates of cell to explore.
     * @return  boolean - true if selected cell is explored, false if action was not valid (already explored).
     */
    boolean explore(ICoordinates coordinates);

    /**
     * Get the field status, representing the game status.
     * @see {@link FieldStatus}
     *
     * @return - FieldStatus
     */
    FieldStatus getFieldStatus();

    /**
     * Mark an unmarked cell, or unmark a marked one.
     *
     * Action not allowed on cell with numeric face value.
     * @param   coordinates - cell coordinates
     * @return  boolean - true if action performed, false is action was not allowed.
     * @throws  IllegalArgumentException if coordinates are not valid
     */
    boolean toggleMarkedCell(ICoordinates coordinates);
}
