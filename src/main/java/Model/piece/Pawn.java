package Model.piece;

import Model.position.Position;
import Model.position.Row;
import Model.team.Team;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {

    private int whiteFirstRow = 2;
    private int blackFirstRow = 7;
    public Pawn(Team team, Position position) {
        super("폰", 1, team, position);
    }

    @Override
    public List<Position> getAllNextPosition() {
        List<Position> positionList = new ArrayList<>();

//        if (isFirstMove() && this.chź) {
//            // 해당 칸에 아무것도 없으면
//            this.position.addPositionIfPossible(team, Row.Two,positionList);
//        }

        // 1칸 전진

        // 대각선 2곳
        return null;
    }

    private boolean isFirstMove() {
        final int firstRow = this.team.isWhite() ?
            whiteFirstRow : blackFirstRow;

        return position.isSameRow(firstRow);
    }
}
