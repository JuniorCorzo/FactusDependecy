package io.github.juniorcorzo.dto.billing;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record BillingPeriodDTO(
    String startDate,
    String startTime,
    String endDate,
    String endTime
) {
}
