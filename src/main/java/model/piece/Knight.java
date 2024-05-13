package model.piece;

import model.chessboard.ChessBoardWrapper;
import model.position.Position;
import model.Team;
import java.util.List;

public class Knight extends AbstractPiece{

    private int[][] possibleMove = {
        {1, 2},
        {-1, 2},
        {1, -2},
        {-1, -2},
        {2, 1},
        {-2, 1},
        {2, -1},
        {-2, -1}
    };
    public Knight(Team team, Position position, ChessBoardWrapper chessBoardWrapper) {
        super("n", 3, team, position, chessBoardWrapper);
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
        return false;
    }
}
