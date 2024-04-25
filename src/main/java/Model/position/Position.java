package Model.position;

public class Position {

    private final Row row;
    private final Column column;

    public Row getRow() {
        return row;
    }

    public Column getColumn() {
        return column;
    }

    public Position(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    public boolean isSameRow(int firstRow) {
        return this.row.getValue() == firstRow;
    }
}
