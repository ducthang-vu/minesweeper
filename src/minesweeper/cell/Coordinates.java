package minesweeper.cell;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A class implementing the ICoordinates interface in a 2D space of 9 * 9, with offset of 1 (origin is 1, 1 instead of
 * 0, 0).
 * <p>
 * Internally the coordinates are stored as if the origin were 0, 0. The offset is only for client users.
 */
public class Coordinates implements ICoordinates {

    /**
     * Get list of all adjacent coordinates lineal values to a given one.
     *
     * @param   lineal - the integer lineal value of the given coordinate
     * @return  list of adjacent coordinates lineal values
     */
    public static List<Integer> getSurrounding(int lineal) {
        int row = lineal / side;
        int column = lineal % side;
        List<Integer> result = new ArrayList<>();
        for (int i = Math.max(0, row - 1); i < Math.min(row + 2, side); i++) {
            for (int j = Math.max(0, column - 1); j < Math.min(column + 2, side); j++) {
                if (i != row || j != column) {
                    result.add(i * side + j);
                }
            }
        }
        return result;
    }

    /**
     * Overloading of {@link #getSurrounding(int)}, accepting a ICoordinates object instead of its lineal value.
     */
    public static List<Integer> getSurrounding(ICoordinates coordinates) {
        int lineal = coordinates.getLineal();
        return getSurrounding(lineal);
    }

    /**
     * The coordinate system of a square of side length.
     */
    private static final int side = 9;

    /**
     * Origin is 0, 0; but may be referenced outside the class with other coordinates.
     */
    private static final int offset = 1;

    private final int x;
    private final int y;

    /**
     * Class constructor.
     * <p>
     * Create new object from string value. The string shall be split by whitespaces, the first word shall be the value
     * of y, and the second of x. An offset of 1 shall be considered, that is: the constructor shall consider the input
     * 1, 1 as the origin of the 2D space, the real values as (0, 0).
     * For example "8 3" shall be converted as (x = 2, y = 7).
     *
     * @param   strCoordinates - the string representing coordinates y and x value.
     */
    public Coordinates(String strCoordinates) {
        String[] coordinates = strCoordinates.split(" ");
        y = Integer.parseInt(coordinates[0]) - offset;
        x = Integer.parseInt(coordinates[1]) - offset;
    }

    /**
     * Class constructor.
     * <p>
     * Create new object from lineal value. For example the lineal value 0 shall be converted into (x = 0, y = 0).
     *
     * @param   linealValue - a integer.
     */
    public Coordinates(int linealValue) {
        y = linealValue / side;
        x = linealValue % side;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLineal() {
        return y * side + x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
