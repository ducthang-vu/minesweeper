package minesweeper.game;


import minesweeper.cell.ICoordinates;
import minesweeper.field.IField;

/**
 * Command exploring a cell of a given game field.
 *
 * @see {@link minesweeper.field.Field}
 */
public class Explore implements Command {
    private final IField field;
    private final ICoordinates coordinates;
    private boolean success;

    /**
     * Sole constructor.
     *
     * @param field - game field.
     * @param coordinates - coordinates to be explored.
     */
    public Explore(IField field, ICoordinates coordinates) {
        this.field = field;
        this.coordinates = coordinates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        success = field.explore(coordinates);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSuccess() {
        return success;
    }
}
