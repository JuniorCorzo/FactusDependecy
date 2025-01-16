package io.github.juniorcorzo.dto.billing.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record BillEventsResponseDTO(
        String number,
        String cude,
        String eventCode,
        String effectiveDate,
        String effectiveTime
) {
}
