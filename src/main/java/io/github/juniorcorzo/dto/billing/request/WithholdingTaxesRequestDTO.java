package io.github.juniorcorzo.dto.billing.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

import java.io.Serializable;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public record WithholdingTaxesRequestDTO(
        String code,
        String withholdingTaxRate
) implements Serializable {
}
