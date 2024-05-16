package model.piece;

import java.util.ArrayList;
import java.util.List;
import model.ChessBoard;
import model.Team;
import model.position.Column;
import model.position.Position;
import model.position.Row;

public class PieceCollectionGenerator {
    public static PieceCollection pieceCollection(ChessBoard chessBoard) {
        List<AbstractPiece> whitePieceList = getPieces(Team.WHITE, chessBoard);
        List<AbstractPiece> blackPieceList = getPieces(Team.BLACK, chessBoard);

        whitePieceList.addAll(blackPieceList);

        return new PieceCollection(whitePieceList);
    }

    private static List<AbstractPiece> getPieces(Team team, ChessBoard chessBoard) {
        List<AbstractPiece> pieceList = new ArrayList<>();
        Row majorStartRow = team.isWhite() ? Row.getWhiteMajorPieceStartRow() : Row.getBlackMajorPieceStartRow();

        pieceList.add(new Rook(team, Position.of(majorStartRow, Column.A), chessBoard));
        pieceList.add(new Knight(team, Position.of(majorStartRow, Column.B), chessBoard));
        pieceList.add(new Bishop(team, Position.of(majorStartRow, Column.C), chessBoard));
        pieceList.add(new Queen(team, Position.of(majorStartRow, Column.D), chessBoard));
        pieceList.add(new King(team, Position.of(majorStartRow, Column.E), chessBoard));
        pieceList.add(new Bishop(team, Position.of(majorStartRow, Column.F), chessBoard));
        pieceList.add(new Knight(team, Position.of(majorStartRow, Column.G), chessBoard));
        pieceList.add(new Rook(team, Position.of(majorStartRow, Column.H), chessBoard));

        addPawn(team, pieceList, chessBoard);

        return pieceList;
    }

    private static void addPawn(Team team, List<AbstractPiece> pieceList, ChessBoard chessBoard) {
        Row firstRow = team.isWhite() ? Row.getWhitePawnStartRow() : Row.getBlackPawnStartRow();

        for (Column column : Column.values()) {
            Position position = Position.of(firstRow, column);
            Pawn pawn = new Pawn(team, position, chessBoard);

            pieceList.add(pawn);
        }
    }
}
