package model.dto;

import model.piece.AbstractPiece;

public class PieceInfoDto {

    private final String name;
    private final int point;

    public PieceInfoDto(AbstractPiece piece) {
        this.name = piece.getName();
        this.point = piece.getPoint();
    }

    public int getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return name + " " + point;
    }
}
