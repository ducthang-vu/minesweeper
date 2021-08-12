package minesweeper.cell;

/**
 * Interface for a single cell of a minesweeper field
 */
public interface ICell {
    /**
     * Get cell coordinates.
     *
     * @return  cell's coordinates (ICoordinates)
     */
    ICoordinates getCoordinates();

    /**
     * Get cell face value (CellFaceValue)
     *
     * @return  cell face value (CellFaceValue)
     */
    CellFaceValue getFaceValue();

    /**
     * Get cell value (CellValue)
     *
     * @return  cell value (CellValue)
     */
    CellValue getValue();

    /**
     * Explores the cell.
     * <p>
     * Done by the player when he reckons the cell do not contain a mine. If cell is indeed a mine, the game ended with
     * the player losing.
     * <p>
     * Action is not allowed if the cell is already been explored. See also {@link #isExplored isExplored} method.
     *
     * @return  boolean - true if action is performed, false if the action is not allowed.
     */
    boolean explore();

    /**
     * Tests whether the cells has been marked by user as potential mine.
     *
     * @return  boolean
     */
    boolean isMarked();

    /**
     * Tests whether the cell has been already explored by the player.
     *
     * @return  boolean
     */
    boolean isExplored();
    /**
     * Tests whether the cells has value "MINE" (CellValue).
     *
     * @return  boolean
     */
    boolean isMine();

    /**
     * Marks/unmarks the cell as potential mine.
     * <p>
     * Cannot mark an explored cell with numeric FaceValue.
     *
     * @return  boolean - if action is allowed and done, otherwise false
     */
    boolean mark();

    /**
     * Set cell CellValue.
     *
     * @param value - the value
     */
    void setValue(CellValue value);

}
