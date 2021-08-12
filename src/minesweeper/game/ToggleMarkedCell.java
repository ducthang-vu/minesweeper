package minesweeper.game;

import minesweeper.cell.ICoordinates;
import minesweeper.field.IField;

/**
 * Command marking or unmarking a cell of a given game field.
 *
 * @see {@link minesweeper.field.Field}
 */
public class ToggleMarkedCell implements Command {
    private final IField field;
    private final ICoordinates coordinates;
    private boolean success;

    /**
     * Sole constructor.
     *
     * @param field - game field.
     * @param coordinates - coordinates to be marked or unmarked.
     */
    public ToggleMarkedCell(IField field, ICoordinates coordinates) {
        this.field = field;
        this.coordinates = coordinates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        success = field.toggleMarkedCell(coordinates);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSuccess() {
        return success;
    }
}
