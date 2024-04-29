package Model.chessboard;

import Model.piece.AbstractPiece;
import Model.piece.PieceCollection;
import Model.position.Column;
import Model.position.GridPosition;
import Model.position.Position;
import Model.position.Row;
import Model.team.Team;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardWrapper {

    private final GridPosition gridPosition;
    private Map<Position, AbstractPiece> chessBoard;
    private PieceCollection pieceCollection;
    public ChessBoardWrapper() {
        this.gridPosition = new GridPosition();
        this.chessBoard = new HashMap<>();
        this.pieceCollection = PieceCollection.pieceCollection(gridPosition, this);
        initPiece();
    }

    private void initPiece() {
        for (AbstractPiece piece : pieceCollection.getPieceList()) {
            chessBoard.put(piece.getPosition(), piece);
        }
    }

    public boolean isEnemyHere(Position nextPosition, Team currentTeam) {
        if (chessBoard.containsKey(nextPosition)) {
            AbstractPiece abstractPiece = chessBoard.get(nextPosition);
            return !abstractPiece.isSameTeam(currentTeam);
        }
        return false;
    }
    public boolean isTeamHere(Position nextPosition, Team currentTeam) {
        if (chessBoard.containsKey(nextPosition)) {
            AbstractPiece abstractPiece = chessBoard.get(nextPosition);
            return abstractPiece.isSameTeam(currentTeam);
        }
        return false;
    }
    public boolean isNothingHere(Position nextPosition) {
        return !chessBoard.containsKey(nextPosition);
    }

    public void eliminateIfEnemyExist(Position nextPosition, Team team) {
        if (isEnemyHere(nextPosition, team)) {
            chessBoard.remove(nextPosition);
            pieceCollection.removePiece(nextPosition);
        }
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
        if (chessBoard.containsKey(currentPosition)) {
            AbstractPiece abstractPiece = chessBoard.get(currentPosition);
            abstractPiece.move(nextPosition);
        }
    }
}
