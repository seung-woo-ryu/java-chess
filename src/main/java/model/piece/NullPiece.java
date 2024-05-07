package model.piece;

import java.util.Collections;
import java.util.List;
import model.position.Position;
import model.Team;

public class NullPiece extends AbstractPiece {

    private static NullPiece nullPiece;

    public NullPiece() {
        super(" ", 0, Team.NOTHING, null, null);
    }

    @Override
    public List<Position> getAllNextPosition() {
        return Collections.EMPTY_LIST;
    }

    public static AbstractPiece getEmptyPiece() {
        // todo: 싱글톤 생성. 얘 이러면 동시성 문제 있나?
        if (nullPiece == null) {
            nullPiece = new NullPiece();
        }

        return nullPiece;
    }
    @Override
    public boolean isKing() {
        return false;
    }
}
