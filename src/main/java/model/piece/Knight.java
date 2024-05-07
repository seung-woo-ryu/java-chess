package model.piece;

import java.util.ArrayList;
import model.chessboard.ChessBoardWrapper;
import model.position.Column;
import model.position.Position;
import model.position.Row;
import model.Team;
import java.util.List;

public class Knight extends AbstractPiece{

    private int[][] possibleMove = {
        {1, 2},
        {-1, 2},
        {1, -2},
        {-1, -2},
        {2, 1},
        {-2, 1},
        {2, -1},
        {-2, -1}
    };
    public Knight(Team team, Position position, ChessBoardWrapper chessBoardWrapper) {
        super("n", 3, team, position, chessBoardWrapper);
    }

    @Override
    public List<Position> getAllNextPosition() {
        List<Position> possiblePosition = new ArrayList<>();

        for (int[] move : possibleMove) {
            addPossiblePosition(move, possiblePosition);
        }

        return possiblePosition;
    }

    private void addPossiblePosition(int[] move,List<Position> possiblePosition) {
        Row row = this.position.getRow();
        Column column = this.position.getColumn();
        if (!chessBoardWrapper.isChessBoardIn(Position.unmodifiablePosition(row,column), move[0], move[1])) {
            return;
        }

        row = row.move(move[0]);
        column = column.move(move[1]);

        Position nextPosition = Position.unmodifiablePosition(row, column);
        if (chessBoardWrapper.isTeamHere(nextPosition, this.team)) {
            return;
        }
        possiblePosition.add(nextPosition);
    }
    @Override
    public boolean isKing() {
        return false;
    }
}
