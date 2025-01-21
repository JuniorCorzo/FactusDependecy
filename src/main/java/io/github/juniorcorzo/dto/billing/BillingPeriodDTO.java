package io.github.juniorcorzo.dto.billing;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record BillingPeriodDTO(
    String startDate,
    String startTime,
    String endDate,
    String endTime
) {
    public BillingPeriodDTO(String startDate, String startTime, String endDate, String endTime) {
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    public BillingPeriodDTO(String endTime, String endDate, String startDate) {
        this(startDate, "", endDate, endTime);
    }
}
