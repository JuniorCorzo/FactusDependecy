package io.github.juniorcorzo.dto.tributes;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TributesDTO(
        int id,
        String code,
        String name,
        String description
) {
}
