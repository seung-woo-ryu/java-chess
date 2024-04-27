package Model.piece;

import Model.position.Position;
import java.util.List;

public interface Piece {
    List<Position> getAllNextPosition();

    void move(Position position);
}
