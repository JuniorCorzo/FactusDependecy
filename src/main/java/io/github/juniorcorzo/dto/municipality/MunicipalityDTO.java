package io.github.juniorcorzo.dto.municipality;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record MunicipalityDTO(
        int id,
        String code,
        String name,
        String department
) {
}
