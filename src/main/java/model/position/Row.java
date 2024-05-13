package model.position;

public enum Row {
    EIGHT(8,"8"),
    SEVEN(7,"7"),
    SIX(6,"6"),
    FIVE(5,"5"),
    FOUR(4,"4"),
    THREE(3,"3"),
    TWO(2,"2"),
    ONE(1,"1");

    private int value;
    private String name;

    public int getValue() {
        return value;
    }

    public static Row nameOf(char value) {
        String stringValue = String.valueOf(value);
        for (Row row : Row.values()) {
            if (row.getName().equals(stringValue)) {
                return row;
            }
        }
        throw new IllegalArgumentException("Row는 1~8만 가능합니다");
    }

    public Row move(int step) {
        for (Row row : Row.values()) {
            if (row.getValue() == (this.value + step)) {
                return row;
            }
        }

        throw new IllegalArgumentException("가능한 Row(1~8)에서 벗어났습니다");
    }
    public boolean isPossibleMove(int step) {
        return (Row.ONE.value <= this.value + step) && (this.value + step <= Row.EIGHT.value);
    }

    public String getName() {
        return name;
    }

    public static Row getWhitePawnStartRow() {
        return Row.TWO;
    }
    public static Row getWhiteMajorPieceStartRow() {
        return Row.ONE;
    }
    public static Row getBlackPawnStartRow() {
        return Row.SEVEN;
    }
    public static Row getBlackMajorPieceStartRow() {
        return Row.EIGHT;
    }
    Row(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
