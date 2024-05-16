package model.piece;

import model.ChessBoard;
import java.util.ArrayList;
import model.position.Position;
import model.Team;
import java.util.List;

public class Pawn extends AbstractPiece {
    private final int TWO_STEP;
    private final int ONE_STEP;
    private final int FORWARD_COLUMN_STEP;
    private final int LEFT_COLUMN_STEP;
    private final int RIGHT_COLUMN_STEP;
    private final int[] oneStepForwardMove;
    private final int[] twoStepForwardMove;
    private final int[] leftDiagonalMove;
    private final int[] rightDiagonalMove;
    public Pawn(Team team, Position position, ChessBoard chessBoard) {
        super("p", 1, team, position, chessBoard);

        TWO_STEP = 2 * team.getDirection();
        ONE_STEP = 1 * team.getDirection();
        FORWARD_COLUMN_STEP = 0;
        LEFT_COLUMN_STEP = -1;
        RIGHT_COLUMN_STEP = 1;

        oneStepForwardMove = new int[]{ONE_STEP, FORWARD_COLUMN_STEP};
        twoStepForwardMove = new int[]{TWO_STEP, FORWARD_COLUMN_STEP};
        leftDiagonalMove = new int[]{ONE_STEP, LEFT_COLUMN_STEP};
        rightDiagonalMove = new int[]{ONE_STEP, RIGHT_COLUMN_STEP};
    }

    @Override
    public List<Position> getAllNextPosition() {
        final int[][] possibleMove = addPawnPossibleMove();

        return getPosition(possibleMove);
    }

    private int[][] addPawnPossibleMove() {
        List<int[]> possibleMove = new ArrayList<>();

        if (isNothingHere(oneStepForwardMove)) {
            possibleMove.add(oneStepForwardMove);
        }
        if (isNothingHere(twoStepForwardMove) && isFirstMove()) {
            possibleMove.add(twoStepForwardMove);
        }

        if (isEnemyExistOnDiagonal(LEFT_COLUMN_STEP)) {
            possibleMove.add(leftDiagonalMove);
        }
        if (isEnemyExistOnDiagonal(RIGHT_COLUMN_STEP)) {
            possibleMove.add(rightDiagonalMove);
        }

        return possibleMove.toArray(int[][]::new);
    }

    private boolean isNothingHere(int[] move) {
        final int rowStep = move[0];
        final int columnStep = move[1];
        if (chessBoard.isInBoardAfterMove(this.position, rowStep, columnStep)) {
            Position nextPosition = this.position.getNextPosition(rowStep, columnStep);
            return chessBoard.isNothingHere(nextPosition);
        }
        return false;
    }

    private boolean isEnemyExistOnDiagonal(int columnDirection) {
        if (chessBoard.isInBoardAfterMove(this.position, ONE_STEP, columnDirection)) {
            Position nextPosition = this.position.getNextPosition(ONE_STEP, columnDirection);
            if (chessBoard.isEnemyHere(nextPosition, this.team)) {
                return true;
            }
        }

        return false;
    }

    private boolean isFirstMove() {
        return this.moveCnt == 0;
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
