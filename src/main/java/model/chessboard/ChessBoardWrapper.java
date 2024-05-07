package model.chessboard;

import java.util.List;
import java.util.Stack;
import model.MoveHistory;
import model.piece.AbstractPiece;
import model.piece.PieceCollection;
import model.position.Column;
import model.position.GridPosition;
import model.position.Position;
import model.position.Row;
import model.Team;

public class ChessBoardWrapper {

    private final GridPosition gridPosition;
    private PieceCollection pieceCollection;
    private Stack<MoveHistory> moveHistories;
    public ChessBoardWrapper() {
        this.gridPosition = new GridPosition();
        this.pieceCollection = PieceCollection.pieceCollection(gridPosition, this);
        this.moveHistories = new Stack<>();
    }

    public AbstractPiece getPiece(Position position) {
        return pieceCollection.getPiece(position);
    }

    public boolean isEnemyHere(Position nextPosition, Team currentTeam) {
        return pieceCollection.isEnemyHere(nextPosition, currentTeam);
    }
    public boolean isTeamHere(Position nextPosition, Team currentTeam) {
        return pieceCollection.isTeamHere(nextPosition, currentTeam);
    }
    public boolean isNothingHere(Position nextPosition) {
        return pieceCollection.isNothingHere(nextPosition);
    }

    public AbstractPiece eliminateIfEnemyExist(Position nextPosition, Team team) {
        if (isEnemyHere(nextPosition, team)) {
            AbstractPiece eliminatedPiece = pieceCollection.getPiece(nextPosition);
            pieceCollection.removePiece(nextPosition);
            return eliminatedPiece;
        }
        return null;
    }

    public GridPosition getGridPosition() {
        return gridPosition;
    }

    public boolean isChessBoardIn(Position position, int rowStep, int columnStep) {
        // todo: 넘 별론데
        try {
            Row row = position.getRow();
            Column column = position.getColumn();

            row.move(rowStep);
            column.move(columnStep);

            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    public void move(Position currentPosition, Position nextPosition) {
        if (!pieceCollection.isNothingHere(currentPosition)) {
            try {
                AbstractPiece piece = pieceCollection.getPiece(currentPosition);
                piece.move(nextPosition);

                rollbackIfChecked(piece, currentPosition, nextPosition);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }
    }

    private void undoMove(AbstractPiece piece, Position currentPosition, Position nextPosition) {
        piece.forceMove(currentPosition);
        restorePieceIfEliminated();
    }

    private void restorePieceIfEliminated() {
        MoveHistory pop = moveHistories.pop();

        if (pop.isEliminated()) {
            AbstractPiece eliminatedPiece = pop.getEliminatedPiece();
            pieceCollection.addPiece(eliminatedPiece);
        }
    }

    private void rollbackIfChecked(AbstractPiece abstractPiece, Position currentPosition, Position nextPosition) {
        Team team = abstractPiece.getTeam();

        if (isChecked(team)) {
            undoMove(abstractPiece,currentPosition,nextPosition);

            throw new IllegalArgumentException("체크 상태라 움직일 수가 없습니다");
        }
    }

    // ToDo: Enhanced Loop안에서 원소 지우니깐 ConcurrentModificationException 발생
    public boolean isCheckmate(Team team) {
        for (AbstractPiece piece : pieceCollection.findAllPieceWithSameTeam(team)) {
            for (Position nextPosition : piece.getAllNextPosition()) {
                final Position currentPosition = piece.getPosition();
                piece.move(nextPosition);
                if (!isChecked(team)) {
                    return false;
                }
                undoMove(piece,currentPosition,nextPosition);
            }
        }
        return true;
    }

    public boolean isChecked(Team team) {
        Position kingPosition = findKingPosition(team);
        // 반대 팀의 모든 가능한 포지션들을 찾는다.
        List<Position> allPossiblePosition = pieceCollection.findAllPossiblePosition(
            team.getOpposite());

        for (Position position : allPossiblePosition) {
            if (position.equals(kingPosition)) {
                return true;
            }
        }

        return false;
    }

    private Position findKingPosition(Team team) {
        return pieceCollection.getKingPosition(team)
            .orElseThrow(()->new IllegalArgumentException("진행중인 게임에는 킹이 존재해야합니다"));
    }

    public void putHistory(Position position, Position nextPosition, AbstractPiece eliminatedPiece) {
        moveHistories.push(MoveHistory.getEliminatedHistory(position, nextPosition, eliminatedPiece));
    }
}