package minesweeper.cell;

/**
 * Interface for object representing 2D coordinates objects.
 * <p>
 * A coordinates object has a "lineal" int value, which is the corresponding unique coordinate in a one-dimensional
 * space, the first value being 0. For example, in a (4 * 4) space the coordinate (0, 0) would be the first (lineal = 0)
 * and (3, 3) the last (lineal = 15).
 */
public interface ICoordinates {
    /**
     * Get the lineal value
     * @return  int - the lineal value.
     */
    int getLineal();
}
