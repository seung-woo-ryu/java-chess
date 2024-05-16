package model.piece;

import model.ChessBoard;
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
    public Knight(Team team, Position position, ChessBoard chessBoard) {
        super("n", 3, team, position, chessBoard);
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
