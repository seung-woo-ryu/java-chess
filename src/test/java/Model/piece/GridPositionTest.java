package Model.piece;

import Model.position.Column;
import Model.position.GridPosition;
import Model.position.Position;
import Model.position.Row;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GridPositionTest {

    @Test
    @DisplayName("수정 불가능한 64개의 Position 생성")
    void UNMODIFIABLE_LIST_생성() {
        // given
        GridPosition gridPosition = new GridPosition();
        List<Position> grid = gridPosition.getGrid();

        // when && then
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            grid.add(Position.unmodifiablePosition(Row.EIGHT, Column.C));
        });
    }

}