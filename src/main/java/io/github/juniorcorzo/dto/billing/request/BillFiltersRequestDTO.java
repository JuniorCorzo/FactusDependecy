package io.github.juniorcorzo.dto.billing.request;

public record BillFiltersRequestDTO(
        String identification,
        String names,
        String number,
        String prefix,
        String referenceCode,
        String status
) {
}
