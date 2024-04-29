package Model.piece;

import Model.chessboard.ChessBoardWrapper;
import Model.position.Column;
import Model.position.GridPosition;
import Model.position.Position;
import Model.position.Row;
import Model.team.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PieceCollection {
    private final List<AbstractPiece> pieceList;
    private final Team team;

    private PieceCollection(List<AbstractPiece> pieceList, Team team) {
        this.pieceList = pieceList;
        this.team = team;
    }

    public void removePiece(Position position) {
        pieceList.stream()
            .filter(piece -> !piece.isSamePosition(position))
            .collect(Collectors.toList());
    }

    public static PieceCollection pieceCollection(GridPosition gridPosition, ChessBoardWrapper chessBoardWrapper) {
        List<AbstractPiece> whitePieceList = getPieces(gridPosition,Team.WHITE, chessBoardWrapper);
        List<AbstractPiece> blackPieceList = getPieces(gridPosition,Team.BLACK, chessBoardWrapper);

        whitePieceList.addAll(blackPieceList);

        return new PieceCollection(whitePieceList, Team.WHITE);
    }

    private static List<AbstractPiece> getPieces(GridPosition gridPosition, Team team, ChessBoardWrapper chessBoardWrapper) {
        List<AbstractPiece> pieceList = new ArrayList<>();
        Row majorStartRow = team.isWhite() ? Row.getWhiteMajorPieceStartRow() : Row.getBlackMajorPieceStartRow();

        pieceList.add(new Rook(team, gridPosition.getPosition(majorStartRow, Column.A), chessBoardWrapper));
        pieceList.add(new Rook(team, gridPosition.getPosition(majorStartRow, Column.H), chessBoardWrapper));
        pieceList.add(new Knight(team, gridPosition.getPosition(majorStartRow, Column.B), chessBoardWrapper));
        pieceList.add(new Knight(team, gridPosition.getPosition(majorStartRow, Column.G), chessBoardWrapper));
        pieceList.add(new Bishop(team, gridPosition.getPosition(majorStartRow, Column.C), chessBoardWrapper));
        pieceList.add(new Bishop(team, gridPosition.getPosition(majorStartRow, Column.F), chessBoardWrapper));
        pieceList.add(new Queen(team, gridPosition.getPosition(majorStartRow, Column.D), chessBoardWrapper));
        pieceList.add(new King(team, gridPosition.getPosition(majorStartRow, Column.E), chessBoardWrapper));

        addPawn(gridPosition, team, pieceList, chessBoardWrapper);

        return pieceList;
    }

    private static void addPawn(GridPosition gridPosition, Team team, List<AbstractPiece> pieceList, ChessBoardWrapper chessBoardWrapper) {
        Row firstRow = team.isWhite() ? Row.getWhitePawnStartRow() : Row.getBlackPawnStartRow();

        for (Column column : Column.values()) {
            Position position = gridPosition.getPosition(firstRow, column);
            Pawn pawn = new Pawn(team, position, chessBoardWrapper);

            pieceList.add(pawn);
        }
    }

    public List<AbstractPiece> getPieceList() {
        return pieceList;
    }
}
