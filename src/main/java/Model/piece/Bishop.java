package Model.piece;

import Model.chessboard.ChessBoardWrapper;
import Model.position.Position;
import Model.team.Team;
import java.util.List;

public class Bishop extends AbstractPiece{

    public Bishop(Team team,Position position, ChessBoardWrapper chessBoardWrapper) {
        super("비숍", 3, team, position, chessBoardWrapper);
    }

    @Override
    public List<Position> getAllNextPosition() {
        return null;
    }
}
