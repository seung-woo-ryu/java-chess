package model;

import model.piece.AbstractPiece;
import model.position.Position;



public class MoveHistory {

    private final Position fromPosition;
    private final Position toPosition;
    private final boolean isEliminated;
    private final AbstractPiece eliminatedPiece;

    public Position getFromPosition() {
        return fromPosition;
    }

    public Position getToPosition() {
        return toPosition;
    }

    public boolean isEliminated() {
        return isEliminated;
    }

    public AbstractPiece getEliminatedPiece() {
        return eliminatedPiece;
    }

    public MoveHistory(Position fromPosition, Position toPosition, AbstractPiece eliminatedPiece) {
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
        this.isEliminated = eliminatedPiece != null;
        this.eliminatedPiece = eliminatedPiece;
    }

    public static MoveHistory getEliminatedHistory(Position fromPosition, Position toPosition, AbstractPiece eliminatedPiece) {
        return new MoveHistory(fromPosition, toPosition, eliminatedPiece);
    }

    public static MoveHistory getMoveHistory(Position fromPosition, Position toPosition) {
        return new MoveHistory(fromPosition, toPosition, null);
    }
}
