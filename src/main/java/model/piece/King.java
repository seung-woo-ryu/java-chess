package model.piece;

import model.ChessBoard;
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
    public King(Team team, Position position, ChessBoard chessBoard) {
        super("k", 0, team, position, chessBoard);
    }
    @Override
    public boolean isNullPiece() {
        return false;
    }
    @Override
    public List<Position> getAllNextPosition() {
        return getPosition(possibleMove);
    }
    @Override
    public boolean isKing() {
        return true;
    }
}
