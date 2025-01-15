package io.github.juniorcorzo.dto.measurement;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record MeasurementUnitsDTO(
        int id,
        String code,
        String name
) {
}
