package Model.piece;

import Model.chessboard.ChessBoardWrapper;
import Model.position.Position;
import Model.team.Team;
import java.util.List;

public class Queen extends AbstractPiece{


    public Queen(Team team, Position position, ChessBoardWrapper chessBoardWrapper) {
        super("퀸", 9, team, position, chessBoardWrapper);
    }

    @Override
    public List<Position> getAllNextPosition() {
        return null;
    }
}
