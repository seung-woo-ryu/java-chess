package model.piece;

import controller.ChessBoardWrapper;
import model.position.GridPosition;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractTest {
    protected ChessBoardWrapper chessBoardWrapper;
    protected GridPosition gridPosition;

    @BeforeEach
    void SET_UP() {
        chessBoardWrapper = new ChessBoardWrapper();
        gridPosition = chessBoardWrapper.getGridPosition();
    }

}
