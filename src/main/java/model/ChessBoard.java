package model;

import java.util.Stack;
import model.dto.StatusDto;
import model.piece.AbstractPiece;
import model.piece.NullPiece;
import model.piece.PieceCollection;
import model.piece.PieceCollectionGenerator;
import model.position.Column;
import model.position.Position;
import model.position.Row;

public class ChessBoard {
    private PieceCollection pieceCollection;
    private Stack<MoveHistory> moveHistories;
    public ChessBoard() {
        this.pieceCollection = PieceCollectionGenerator.pieceCollection(this);
        this.moveHistories = new Stack<>();
    }

    public AbstractPiece getPiece(Position position) {
        return pieceCollection.getPieceOfPosition(position);
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
            return removePiece(nextPosition);
        }
        return NullPiece.getEmptyPiece();
    }
    private AbstractPiece removePiece(Position nextPosition) {
        AbstractPiece eliminatedPiece = pieceCollection.getPieceOfPosition(nextPosition);
        pieceCollection.removePiece(eliminatedPiece);
        return eliminatedPiece;
    }
    public boolean isInBoardAfterMove(Position position, int rowStep, int columnStep) {
        final Row row = position.getRow();
        final Column column = position.getColumn();
        return row.isPossibleMove(rowStep) && column.isPossibleMove(columnStep);
    }
    public void move(Position currentPosition, Position nextPosition) {
        try {
            AbstractPiece piece = pieceCollection.getPieceOfPosition(currentPosition);
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
