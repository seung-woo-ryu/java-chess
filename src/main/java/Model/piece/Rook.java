package Model.piece;

import Model.position.Position;
import Model.team.Team;
import java.util.List;

public class Rook extends AbstractPiece{

    public Rook(Team team, Position position) {
        super("룩", 5, team, position);
    }

    @Override
    public List<Position> getAllNextPosition() {
        return null;
    }
}
