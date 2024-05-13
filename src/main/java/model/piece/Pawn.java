package model.piece;

import model.chessboard.ChessBoardWrapper;
import model.position.Column;
import model.position.GridPosition;
import model.position.Position;
import model.position.Row;
import model.Team;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {

    private int whiteFirstRow = 2;
    private int blackFirstRow = 7;
    public Pawn(Team team, Position position, ChessBoardWrapper chessBoardWrapper) {
        super("p", 1, team, position, chessBoardWrapper);
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
            moveForwardSpace(positionList, twoStep);
        }
        // 1칸 전진
        moveForwardSpace(positionList, oneStep);
        // 대각선 이동
        moveDiagonallyOneSpaceIfEnemyExist(gridPosition, positionList, Column.getLeftDirection());
        moveDiagonallyOneSpaceIfEnemyExist(gridPosition, positionList, Column.getRightDirection());
        return positionList;
    }

    private void moveDiagonallyOneSpaceIfEnemyExist(GridPosition gridPosition, List<Position> positionList, int columnStep) {
        int rowStep = this.team.getDirection();

        if (chessBoardWrapper.isPossibleStep(this.position, rowStep, columnStep)) {
            Position diagonalMove = this.position.getNextPosition(rowStep, columnStep);
            if (chessBoardWrapper.isEnemyHere(diagonalMove, this.team)) {
                positionList.add(diagonalMove);
            }
        }
    }

    private void moveForwardSpace(List<Position> positionList,int rowStep) {
        int columnStep = 0;

        if (chessBoardWrapper.isPossibleStep(this.position, rowStep, columnStep)) {
            Position nextPosition = this.position.getNextPosition(rowStep, columnStep);
            if (chessBoardWrapper.isNothingHere(nextPosition)) {
                positionList.add(nextPosition);
            }
        }
    }

    private boolean isFirstMove() {
        final int firstRow = this.team.isWhite() ?
            whiteFirstRow : blackFirstRow;

        return this.position.isSameRow(firstRow);
    }
    @Override
    public boolean isKing() {
        return false;
    }
    @Override
    public boolean isNullPiece() {
        return false;
    }
}
