package io.github.juniorcorzo.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record LinkDTO(
        String url,
        String label,
        boolean active,
        Integer page
) {
}
