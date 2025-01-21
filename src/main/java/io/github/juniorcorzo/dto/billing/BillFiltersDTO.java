package io.github.juniorcorzo.dto.billing;

public record BillFiltersDTO(
        String identification,
        String names,
        String number,
        String prefix,
        String referenceCode,
        String status
) {
}
