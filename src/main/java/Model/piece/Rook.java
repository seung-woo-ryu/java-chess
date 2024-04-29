package Model.piece;

import Model.chessboard.ChessBoardWrapper;
import Model.position.Position;
import Model.team.Team;
import java.util.List;

public class Rook extends AbstractPiece{

    public Rook(Team team, Position position, ChessBoardWrapper chessBoardWrapper) {
        super("룩", 5, team, position, chessBoardWrapper);
    }

    @Override
    public List<Position> getAllNextPosition() {
        return null;
    }
}
