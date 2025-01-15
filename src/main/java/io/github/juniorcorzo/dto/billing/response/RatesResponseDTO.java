package io.github.juniorcorzo.dto.billing.response;

public record RatesResponseDTO(
        String code,
        String name,
        String rate
) {
}
