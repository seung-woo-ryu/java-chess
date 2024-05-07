package model.piece;

import model.chessboard.ChessBoardWrapper;
import model.position.GridPosition;
import org.junit.jupiter.api.BeforeEach;
import view.OutputView;

public abstract class AbstractTest {
    protected ChessBoardWrapper chessBoardWrapper;
    protected GridPosition gridPosition;
    protected OutputView outputView;

    @BeforeEach
    void SET_UP() {
        outputView = new OutputView();
        chessBoardWrapper = new ChessBoardWrapper();
        gridPosition = chessBoardWrapper.getGridPosition();
    }

}
