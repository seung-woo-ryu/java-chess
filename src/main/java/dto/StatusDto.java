package dto;

import java.util.List;

public class StatusDto {
    private final List<StatusDetailDto> statusInfoDtoList;

    public StatusDto(List<StatusDetailDto> statusInfoDtoList) {
        this.statusInfoDtoList = statusInfoDtoList;
    }

    @Override
    public String toString() {
        String[] array = statusInfoDtoList.stream()
            .map(statusDetailDto -> statusDetailDto.toString())
            .toArray(String[]::new);
        return String.join("\n", array);
    }
}
