package model.position;

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
    public Position getNextPosition(int rowStep, int columnStep) {
        final Row row = this.row.move(rowStep);
        final Column column = this.column.move(columnStep);

        return new Position(row, column);
    }

    public static Position of(Row row, Column column) {
        return new Position(row, column);
    }

    public static Position of(char rowString, char columnString) {
        Row row = Row.nameOf(rowString);
        Column column = Column.nameOf(columnString);
        return new Position(row, column);
    }

    public static Position nullPosition() {
        return new Position(null, null);
    }

    public boolean isNullPosition() {
        return this.row == null && this.column == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
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
