package model.piece;

import model.position.Position;
import java.util.List;

public interface Piece {
    List<Position> getAllNextPosition();
    void move(Position position);
    void forceMove(Position position);
    boolean isKing();

    boolean isNullPiece();

}
