package model.piece;

import model.chessboard.ChessBoardWrapper;
import model.position.Position;
import model.Team;
import java.util.List;

public class Rook extends AbstractPiece{

    private int[][] directions = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };
    public Rook(Team team, Position position, ChessBoardWrapper chessBoardWrapper) {
        super("r", 5, team, position, chessBoardWrapper);
    }

    @Override
    public List<Position> getAllNextPosition() {
        return getPositionListRepeatedly(directions);
    }
    @Override
    public boolean isKing() {
        return false;
    }
    @Override
    public boolean isNullPiece() {
        return false;
    }
}
