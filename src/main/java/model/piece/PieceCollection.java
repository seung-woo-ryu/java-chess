package model.piece;

import java.util.Optional;
import model.dto.PieceInfoDto;
import model.dto.StatusDetailDto;
import model.dto.StatusDto;
import model.position.Position;
import model.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PieceCollection {
    private final List<AbstractPiece> pieceList;
    public PieceCollection(List<AbstractPiece> pieceList) {
        this.pieceList = pieceList;
    }

    public List<Position> findAllPossiblePosition(Team team) {
        List<Position> allPossiblePosition = new ArrayList<>();

        for (List<Position> col : getEachPiecePossiblePosition(team)) {
            allPossiblePosition.addAll(col);
        }

        return allPossiblePosition;
    }

    private List<List<Position>> getEachPiecePossiblePosition(Team team) {
        return pieceList.stream()
            .filter(piece -> piece.isSameTeam(team))
            .map(piece -> piece.getAllNextPosition())
            .collect(Collectors.toList());
    }

    public void addPiece(AbstractPiece piece) {
        pieceList.add(piece);
    }
    public void removePiece(AbstractPiece piece) {
        pieceList.remove(piece);
    }
    
    public AbstractPiece getPieceOfPosition(Position position) {
        return pieceList.stream()
            .filter(piece -> !piece.isNullPiece() && piece.isSamePosition(position))
            .findFirst()
            .orElse(NullPiece.getEmptyPiece());
    }

    public boolean isEnemyHere(Position nextPosition, Team currentTeam) {
        return pieceList.stream()
            .anyMatch(p -> p.isSamePosition(nextPosition) && !p.isSameTeam(currentTeam));
    }

    public boolean isTeamHere(Position nextPosition, Team currentTeam) {
        return pieceList.stream().anyMatch(p -> p.isSamePosition(nextPosition) && p.isSameTeam(currentTeam));
    }

    public boolean isNothingHere(Position nextPosition) {
        return pieceList.stream().noneMatch(p -> p.isSamePosition(nextPosition));
    }

    public Optional<Position> getKingPosition(Team team) {
        return this.pieceList.stream()
            .filter(piece -> piece.isKing() && piece.isSameTeam(team))
            .map(piece -> piece.getPosition())
            .findAny();
    }

    public List<AbstractPiece> findAllPieceWithSameTeam(Team team) {
        return pieceList.stream()
            .filter(piece -> piece.isSameTeam(team))
            .collect(Collectors.toList());
    }

    public StatusDto getStatus() {
        List<StatusDetailDto> statusDetailDtoList = new ArrayList<>();

        statusDetailDtoList.add(extracted(Team.WHITE));
        statusDetailDtoList.add(extracted(Team.BLACK));

        return new StatusDto(statusDetailDtoList);
    }

    private StatusDetailDto extracted(Team team) {
        List<PieceInfoDto> collect = this.pieceList.stream()
            .filter(piece -> piece.isSameTeam(team))
            .filter(piece -> !piece.isKing())
            .map(piece -> new PieceInfoDto(piece))
            .collect(Collectors.toList());

        int totalScore = getPoint(collect);

        return new StatusDetailDto(team, totalScore, collect);
    }

    private int getPoint(List<PieceInfoDto> collect) {
        return collect.stream()
            .mapToInt(pieceInfoDto -> pieceInfoDto.getPoint())
            .sum();
    }
}
