package Model.piece;

import Model.chessboard.ChessBoardWrapper;
import Model.position.Position;
import Model.team.Team;
import java.util.List;

public class King extends AbstractPiece{

    public King(Team team, Position position, ChessBoardWrapper chessBoardWrapper) {
        super("킹", 0, team, position, chessBoardWrapper);
    }

    @Override
    public List<Position> getAllNextPosition() {
        return null;
    }
}
