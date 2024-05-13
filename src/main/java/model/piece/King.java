package model.piece;

import model.chessboard.ChessBoardWrapper;
import model.position.Position;
import model.Team;
import java.util.List;

public class King extends AbstractPiece{
    private int[][] possibleMove = {
        {1, 1},
        {-1, 1},
        {-1, -1},
        {1, -1},
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };
    public King(Team team, Position position, ChessBoardWrapper chessBoardWrapper) {
        super("k", 0, team, position, chessBoardWrapper);
    }
    @Override
    public boolean isNullPiece() {
        return false;
    }
    @Override
    public List<Position> getAllNextPosition() {
        return getPositionList(possibleMove);
    }
    @Override
    public boolean isKing() {
        return true;
    }
}
