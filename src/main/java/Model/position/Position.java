package Model.position;

import java.util.Objects;

public class Position {

    private final Row row;
    private final Column column;

    public Row getRow() {
        return row;
    }

    public Column getColumn() {
        return column;
    }

    private Position(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    public static Position unmodifiablePosition(Row row, Column column) {
        return new Position(row, column);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    public boolean isSameRow(int firstRow) {
        return this.row.getValue() == firstRow;
    }
}
