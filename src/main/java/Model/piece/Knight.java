package Model.piece;

import Model.chessboard.ChessBoardWrapper;
import Model.position.Position;
import Model.team.Team;
import java.util.List;

public class Knight extends AbstractPiece{

    public Knight(Team team, Position position, ChessBoardWrapper chessBoardWrapper) {
        super("나이트", 3, team, position, chessBoardWrapper);
    }

    @Override
    public List<Position> getAllNextPosition() {
        return null;
    }
}
