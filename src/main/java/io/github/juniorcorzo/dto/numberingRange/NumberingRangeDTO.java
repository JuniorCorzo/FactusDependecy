package io.github.juniorcorzo.dto.numberingRange;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.juniorcorzo.deserializes.NumberingRangeDeserializer;

import java.time.LocalDate;

@JsonDeserialize(using = NumberingRangeDeserializer.class)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record NumberingRangeDTO(
        Integer id,
        String document,
        String prefix,
        long from,
        long to,
        long current,
        String resolutionNumber,
        String startDate,
        String endDate,
        String months,
        String technicalKey,
        boolean isExpired,
        int isActive,
        String createdAt,
        String updatedAt
) {
    public NumberingRangeDTO(Integer id, String document, String prefix, long from, long to, long current, String resolutionNumber, String startDate, String endDate, String months, String technicalKey, boolean isExpired, int isActive, String createdAt, String updatedAt) {
        this.id = id;
        this.document = document;
        this.prefix = prefix;
        this.from = from;
        this.to = to;
        this.current = current;
        this.resolutionNumber = resolutionNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.months = months;
        this.technicalKey = technicalKey;
        this.isExpired = isExpired;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public NumberingRangeDTO(int isActive, boolean isExpired, String technicalKey, String endDate, String startDate, String resolutionNumber, long current, long to, long from, String prefix, String document, Integer id) {
        this(id, document, prefix, from, to, current, resolutionNumber, startDate, endDate, "", technicalKey, isExpired, isActive, null, null);
    }

    public NumberingRangeDTO(String prefix, long from, long to, String resolutionNumber, String startDate, String endDate, String months) {
        this(0, "", prefix, from, to, 0, resolutionNumber, startDate, endDate, months, "", false, 0, null, null);
    }
}
