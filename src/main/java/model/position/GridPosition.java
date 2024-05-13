package model.position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GridPosition {
    private final List<Position> grid;
    public GridPosition() {
        this.grid = init();
    }

    private List<Position> init() {
        List<Position> grid = new ArrayList<>();

        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                grid.add(Position.of(row, column));
            }
        }

        return Collections.unmodifiableList(grid);
    }

    public List<Position> getGrid() {
        return grid;
    }

    public Position getPosition(Row row, Column column) {
        for (Position position : grid) {
            if(position.getRow().equals(row) && position.getColumn().equals(column))
            {
                return position;
            }
        }

        throw new IllegalArgumentException("존재하지 않는 포지션입니다");
    }
}
