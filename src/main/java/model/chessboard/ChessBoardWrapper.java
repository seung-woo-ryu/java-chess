package model.chessboard;

import java.util.Stack;
import model.MoveHistory;
import model.dto.StatusDto;
import model.piece.AbstractPiece;
import model.piece.NullPiece;
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
    public GridPosition getGridPosition() {
        return gridPosition;
    }
    public AbstractPiece eliminateIfEnemyExist(Position nextPosition, Team team) {
        if (isEnemyHere(nextPosition, team)) {
            return removePiece(nextPosition);
        }
        return NullPiece.getEmptyPiece();
    }
    private AbstractPiece removePiece(Position nextPosition) {
        AbstractPiece eliminatedPiece = pieceCollection.getPiece(nextPosition);
        pieceCollection.removePiece(eliminatedPiece);
        return eliminatedPiece;
    }
    public boolean isPossibleStep(Position position, int rowStep, int columnStep) {
        final Row row = position.getRow();
        final Column column = position.getColumn();

        return row.isPossibleMove(rowStep) && column.isPossibleMove(columnStep);
    }
    public void move(Position currentPosition, Position nextPosition) {
        try {
            AbstractPiece piece = pieceCollection.getPiece(currentPosition);
            piece.move(nextPosition);

            rollbackIfChecked(piece, currentPosition);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    private void rollbackIfChecked(AbstractPiece abstractPiece, Position currentPosition) {
        final Team team = abstractPiece.getTeam();

        if (isChecked(team)) {
            undoMove(abstractPiece,currentPosition);

            throw new IllegalArgumentException("체크 상태라 움직일 수가 없습니다");
        }
    }
    private void undoMove(AbstractPiece piece, Position currentPosition) {
        piece.undoMove(currentPosition);
        restorePieceIfEliminated();
    }

    private void restorePieceIfEliminated() {
        MoveHistory moveHistory = moveHistories.pop();

        if (moveHistory.isEliminated()) {
            AbstractPiece eliminatedPiece = moveHistory.getEliminatedPiece();
            pieceCollection.addPiece(eliminatedPiece);
        }
    }

    public boolean isCheckmate(Team team) {
        for (AbstractPiece piece : pieceCollection.findAllPieceWithSameTeam(team)) {
            if (isExistMoveOutOfCheckMate(team, piece)) {
                return false;
            }
        }
        return true;
    }
    private boolean isExistMoveOutOfCheckMate(Team team, AbstractPiece piece) {
        for (Position nextPosition : piece.getAllNextPosition()) {
            final Position currentPosition = piece.getPosition();
            piece.move(nextPosition);
            if (!isChecked(team)) {
                undoMove(piece,currentPosition);
                return true;
            }
            undoMove(piece,currentPosition);
        }
        return false;
    }

    public boolean isChecked(Team team) {
        Position kingPosition = findKingPosition(team);

        if (isExistPiecePossibleCheck(team, kingPosition)) {
            return true;
        }

        return false;
    }

    private boolean isExistPiecePossibleCheck(Team team, Position kingPosition) {
        final Team opposite = team.getOpposite();

        for (Position position : pieceCollection.findAllPossiblePosition(opposite)) {
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
        moveHistories.push(MoveHistory.of(position, nextPosition, eliminatedPiece));
    }
    public StatusDto getStatus() {
        return pieceCollection.getStatus();
    }
}
