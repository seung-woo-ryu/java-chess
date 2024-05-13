package model.piece;

import controller.ChessBoardWrapper;
import model.position.Position;
import model.Team;
import java.util.List;

public class Bishop extends AbstractPiece{
    private int[][] directions = {
        {1, 1},
        {-1, 1},
        {-1, -1},
        {1, -1}
    };
    public Bishop(Team team,Position position, ChessBoardWrapper chessBoardWrapper) {
        super("b", 3, team, position, chessBoardWrapper);
    }

    @Override
    public boolean isNullPiece() {
        return false;
    }
    @Override
    public List<Position> getAllNextPosition() {
        return getPositionListRepeatedly(directions);
    }
    @Override
    public boolean isKing() {
        return false;
    }
}
