package io.github.juniorcorzo.dto.billing.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record WithholdingTaxesResponseDTO(
        String tributeCode,
        String name,
        String value,
        RatesResponseDTO[] rates
) {
    public WithholdingTaxesResponseDTO(String tributeCode, String name, String value, RatesResponseDTO[] rates) {
        this.tributeCode = tributeCode;
        this.name = name;
        this.value = value;
        this.rates = rates;
    }

    public WithholdingTaxesResponseDTO(String tributeCode, String name, String value) {
        this(tributeCode, name, value, null);
    }
}
