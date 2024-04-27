package Model.piece;

import Model.position.Position;
import Model.team.Team;
import java.util.List;

public class King extends AbstractPiece{

    public King(Team team, Position position) {
        super("킹", 0, team, position);
    }

    @Override
    public List<Position> getAllNextPosition() {
        return null;
    }
}
