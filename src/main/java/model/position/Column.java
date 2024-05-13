package model.position;

public enum Column {
    A(1,"a"),
    B(2,"b"),
    C(3,"c"),
    D(4,"d"),
    E(5,"e"),
    F(6,"f"),
    G(7,"g"),
    H(8,"h");

    private final int value;
    private final String name;

    Column(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static int getLeftDirection() {
        return -1;
    }
    public static int getRightDirection() {
        return 1;
    }

    public static Column nameOf(char columnChar) {
        String columnString = String.valueOf(columnChar);
        for (Column column : Column.values()) {
            if (column.getName().equals(columnString)) {
                return column;
            }
        }
        throw new IllegalArgumentException("가능한 Column은 a ~ h입니다");
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public Column move(int step) {
        for (Column column : Column.values()) {
            if (column.getValue() == (this.value + step)) {
                return column;
            }
        }

        throw new IllegalArgumentException("가능한 Column(1~8)에서 벗어났습니다");
    }
    public boolean isPossibleMove(int step) {
        return (Column.A.value <= this.value + step) && (this.value + step <= Column.H.value);
    }
}
