package minesweeper.cell;

/**
 * Enum representing the cell value of minesweeper field. This value is, in principle, hidden to the player, who only
 * see the CellFaceValue.
 */
public enum CellValue {
    SAFE("."),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    MINE("X");

    private final String stringValue;

    /**
     * Sole constructor.
     *
     * @param   stringValue - the CellValue value
     */
    CellValue(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return this.stringValue;
    }

    /**
     * Given a numeric CellValue, returns the next numeric CellValue.
     *
     * For example, given a CellValue of value "5", return the CellValue of "6".
     *
     * @return  the new CellValue.
     * @throws  IllegalArgumentException if given CellValue is not numeric, or its value is "8".
     */
    public CellValue getNext() {
        if (this == CellValue.MINE || this == CellValue.EIGHT) {
            throw new IllegalArgumentException(String.format("Value '%s' not allowed", stringValue));
        }

        if (this == CellValue.SAFE) {
            return CellValue.ONE;
        }

        int nextValue = Integer.parseInt(toString()) + 1;
        String nextValueString = String.valueOf(nextValue);
        for (CellValue value : CellValue.values()) {
           if (value.stringValue.equals(nextValueString)) {
               return value;
           }
        }

        throw new RuntimeException("getNext method unable to return a valid CellValue");
    }

    /**
     * Tests whether a CellValue has a numeric value.
     *
     * @return  boolean
     */
    boolean isNumber() {
        return this != CellValue.MINE && this != CellValue.SAFE;
    }
}
