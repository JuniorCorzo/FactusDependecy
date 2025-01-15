package io.github.juniorcorzo.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PaginationDTO(
        int total,
        int perPage,
        int currentPage,
        int lastPage,
        int from,
        int to,
        List<LinkDTO> links
) {
}

