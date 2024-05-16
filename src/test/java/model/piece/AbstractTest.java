package model.piece;

import model.ChessBoard;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractTest {
    protected ChessBoard chessBoard;

    @BeforeEach
    void SET_UP() {
        chessBoard = new ChessBoard();
    }

}
