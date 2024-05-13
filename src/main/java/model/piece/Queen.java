package model.piece;

import controller.ChessBoardWrapper;
import model.position.Position;
import model.Team;
import java.util.List;

public class Queen extends AbstractPiece{

    private int[][] directions = {
        {1, 1},
        {-1, 1},
        {-1, -1},
        {1, -1},
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };
    public Queen(Team team, Position position, ChessBoardWrapper chessBoardWrapper) {
        super("q", 9, team, position, chessBoardWrapper);
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
