package Model.piece;

import Model.position.Position;
import Model.team.Team;
import java.util.List;

public class Bishop extends AbstractPiece{

    public Bishop(Team team,Position position) {
        super("비숍", 3, team, position);
    }

    @Override
    public List<Position> getAllNextPosition() {
        return null;
    }
}
