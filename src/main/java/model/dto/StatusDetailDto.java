package model.dto;

import java.util.List;
import model.Team;

public class StatusDetailDto {

    private final Team team;
    private final int totalScore;
    private final List<PieceInfoDto> pieceInfoDtoList;

    public StatusDetailDto(Team team, int totalScore, List<PieceInfoDto> pieceInfoDtoList) {
        this.team = team;
        this.totalScore = totalScore;
        this.pieceInfoDtoList = pieceInfoDtoList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String[] array = pieceInfoDtoList.stream()
            .map(pieceInfoDto -> pieceInfoDto.toString())
            .toArray(String[]::new);

        String pieceInfo = String.join(", ", array);

        return sb.append(team).append(": ").append(totalScore).append("점")
            .append("(")
            .append(pieceInfo)
            .append(")")
            .toString();
    }
}
