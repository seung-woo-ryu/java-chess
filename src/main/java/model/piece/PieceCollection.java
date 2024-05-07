package model.piece;

import java.util.Optional;
import model.chessboard.ChessBoardWrapper;
import model.position.Column;
import model.position.GridPosition;
import model.position.Position;
import model.position.Row;
import model.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PieceCollection {
    private final List<AbstractPiece> pieceList;
    private PieceCollection(List<AbstractPiece> pieceList, Team team) {
        this.pieceList = pieceList;
    }

    public List<Position> findAllPossiblePosition(Team team) {
        List<Position> allPossiblePosition = new ArrayList<>();
        List<List<Position>> collect = pieceList.stream()
            .filter(piece -> piece.isSameTeam(team))
            .map(piece -> piece.getAllNextPosition())
            .collect(Collectors.toList());

        for (List<Position> col : collect) {
            allPossiblePosition.addAll(col);
        }

        return allPossiblePosition;
    }

    public void addPiece(AbstractPiece piece) {
        pieceList.add(piece);
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
    public AbstractPiece getPiece(Position position) {
        return pieceList.stream()
            .filter(piece -> piece.getPosition().equals(position))
            .findFirst()
            .orElse(NullPiece.getEmptyPiece());
    }

    public boolean isEnemyHere(Position nextPosition, Team currentTeam) {
        return pieceList.stream()
            .filter(p -> p.getPosition().equals(nextPosition) && !p.isSameTeam(currentTeam))
            .findAny()
            .isPresent();
    }

    public boolean isTeamHere(Position nextPosition, Team currentTeam) {
        return pieceList.stream()
            .filter(p -> p.getPosition().equals(nextPosition) && p.isSameTeam(currentTeam))
            .findAny()
            .isPresent();
    }

    public boolean isNothingHere(Position nextPosition) {
        return pieceList.stream()
            .filter(p -> p.getPosition().equals(nextPosition))
            .findAny()
            .isEmpty();
    }

    public Optional<Position> getKingPosition(Team team) {
        return this.pieceList.stream()
            .filter(piece -> piece.isKing() && piece.isSameTeam(team))
            .findAny()
            .map(piece -> Optional.of(piece.getPosition()))
            .orElse(Optional.empty());
    }

    public List<AbstractPiece> findAllPieceWithSameTeam(Team team) {
        return pieceList.stream()
            .filter(piece -> piece.isSameTeam(team))
            .collect(Collectors.toList());
    }
}
