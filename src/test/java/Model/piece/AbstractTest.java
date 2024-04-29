package Model.piece;

import Model.chessboard.ChessBoardWrapper;
import Model.position.GridPosition;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractTest {
    ChessBoardWrapper chessBoardWrapper;
    GridPosition gridPosition;

    @BeforeEach
    void SET_UP() {
        chessBoardWrapper = new ChessBoardWrapper();
        gridPosition = chessBoardWrapper.getGridPosition();
    }

}
