package Model.piece;

import Model.chessboard.ChessBoardWrapper;
import Model.position.Column;
import Model.position.GridPosition;
import Model.position.Position;
import Model.position.Row;
import Model.team.Team;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {

    private int whiteFirstRow = 2;
    private int blackFirstRow = 7;
    public Pawn(Team team, Position position, ChessBoardWrapper chessBoardWrapper) {
        super("폰", 1, team, position, chessBoardWrapper);
    }

    @Override
    public List<Position> getAllNextPosition() {
        List<Position> positionList = new ArrayList<>();

        GridPosition gridPosition = this.chessBoardWrapper.getGridPosition();
        int direction = this.team.getDirection();
        int twoStep = 2 * direction;
        int oneStep = 1 * direction;

        // 2칸 전진
        if(isFirstMove()){
            moveForwardSpace(gridPosition, positionList, twoStep);
        }
        // 1칸 전진
        moveForwardSpace(gridPosition, positionList, oneStep);
        // 대각선 이동
        moveDiagonallyOneSpaceIfEnemyExist(gridPosition, positionList, Column.getLeftDirection());
        moveDiagonallyOneSpaceIfEnemyExist(gridPosition, positionList, Column.getRightDirection());
        return positionList;
    }

    private void moveDiagonallyOneSpaceIfEnemyExist(GridPosition gridPosition, List<Position> positionList, int columnStep) {
        int rowStep = this.team.getDirection();
        Row currentRow = super.getPosition().getRow();
        Column currentColumn = super.getPosition().getColumn();

        if (chessBoardWrapper.isChessBoardIn(super.getPosition(), rowStep, columnStep)) {
            Position diagonalMove = gridPosition.getPosition(currentRow.move(rowStep), currentColumn.move(columnStep));
            if (chessBoardWrapper.isEnemyHere(diagonalMove, this.team)) {
                positionList.add(diagonalMove);
            }
        }
    }

    private void moveForwardSpace(GridPosition gridPosition, List<Position> positionList,int rowStep) {
        int columnStep = 0;
        Row currentRow = super.getPosition().getRow();
        Column currentColumn = super.getPosition().getColumn();

        if (chessBoardWrapper.isChessBoardIn(super.getPosition(), rowStep, columnStep)) {
            Position nextPosition = gridPosition.getPosition(currentRow.move(rowStep), currentColumn);
            if (chessBoardWrapper.isNothingHere(nextPosition)) {
                positionList.add(nextPosition);
            }
        }
    }

    private boolean isFirstMove() {
        final int firstRow = this.team.isWhite() ?
            whiteFirstRow : blackFirstRow;

        return super.getPosition().isSameRow(firstRow);
    }
}
