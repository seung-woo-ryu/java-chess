package model;

import model.piece.AbstractPiece;
import model.position.Position;



public class MoveHistory {

    private final Position fromPosition;
    private final Position toPosition;
    private final boolean isEliminated;
    private final AbstractPiece eliminatedPiece;

    public boolean isEliminated() {
        return isEliminated;
    }

    public AbstractPiece getEliminatedPiece() {
        return eliminatedPiece;
    }

    public MoveHistory(Position fromPosition, Position toPosition, AbstractPiece eliminatedPiece) {
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
        this.isEliminated = !eliminatedPiece.isNullPiece();
        this.eliminatedPiece = eliminatedPiece;
    }

    public static MoveHistory of(Position fromPosition, Position toPosition, AbstractPiece eliminatedPiece) {
        return new MoveHistory(fromPosition, toPosition, eliminatedPiece);
    }
}
