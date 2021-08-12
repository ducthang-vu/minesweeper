package minesweeper.cell;

/**
 * Enum representing the face value of a cell of minesweeper field. The face value is the cell value seen by the player.
 */
public enum CellFaceValue {
    BLANK("."),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    EXPLORED("/"),
    MARKED("*"),
    MINE("X");

    /**
     * Get, if allowed, a CellFaceValue given the corresponding CellValue object.
     *
     * @param   value - the CellValue for which find the corresponding CellFaceValue
     * @return  the corresponding CellFaceValue
     */
    public static CellFaceValue fromValue(CellValue value) {
        String stringValue = value.toString();
        for (CellFaceValue val : CellFaceValue.values()) {
            if (val.toString().equals(stringValue)) {
                return val;
            }
        }

        throw new IllegalArgumentException(String.format("Value '%s' not allowed", stringValue));
    }

    private final String stringValue;

    /**
     * Sole constructor.
     *
     * @param stringValue - the CellFaceValue value
     */
    CellFaceValue(String stringValue) {
        this.stringValue = stringValue;
    }

    /**
     * Test whether the CellFaceValue has a numeric value.
     *
     * @return boolean
     */
    boolean isNumber() {
        return this != CellFaceValue.BLANK
                && this != CellFaceValue.EXPLORED
                && this != CellFaceValue.MARKED
                && this != CellFaceValue.MINE;
    }

    @Override
    public String toString() {
        return this.stringValue;
    }
}
