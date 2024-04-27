package Model.piece;

import Model.position.Position;
import Model.team.Team;
import java.util.List;

public class Queen extends AbstractPiece{


    public Queen(Team team, Position position) {
        super("퀸", 9, team, position);
    }

    @Override
    public List<Position> getAllNextPosition() {
        return null;
    }
}
