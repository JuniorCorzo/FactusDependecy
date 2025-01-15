package io.github.juniorcorzo.dto.numberingRange;


import lombok.NonNull;


@NonNull
public record NumberingRangeFiltersDTO(
        String id,
        String document,
        String resolutionNumber,
        String technicalKey,
        String isActive
) {
}



