package model.piece;

import model.chessboard.ChessBoardWrapper;
import model.position.Column;
import model.position.Position;
import model.position.Row;
import model.team.Team;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends AbstractPiece{
    private int[][] directions = {
        {1, 1},
        {-1, 1},
        {-1, -1},
        {1, -1}
    };
    public Bishop(Team team,Position position, ChessBoardWrapper chessBoardWrapper) {
        super("b", 3, team, position, chessBoardWrapper);
    }

    @Override
    public List<Position> getAllNextPosition() {
        List<Position> possiblePosition = new ArrayList<>();

        for (int[] direction : directions) {
            addPossiblePosition(direction, possiblePosition);
        }

        return possiblePosition;
    }

    private void addPossiblePosition(int[] direction,List<Position> possiblePosition) {
        Row row = this.position.getRow();
        Column column = this.position.getColumn();
        do {
            if (!chessBoardWrapper.isChessBoardIn(Position.unmodifiablePosition(row,column), direction[0], direction[1])) {
                break;
            }

            row = row.move(direction[0]);
            column = column.move(direction[1]);

            Position nextPosition = Position.unmodifiablePosition(row, column);
            if (chessBoardWrapper.isTeamHere(nextPosition, this.team)) {
                break;
            }
            possiblePosition.add(nextPosition);
        } while (true);
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
