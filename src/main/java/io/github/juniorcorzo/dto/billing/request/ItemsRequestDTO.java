package io.github.juniorcorzo.dto.billing.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public record ItemsRequestDTO(
        String codeReference,
        String name,
        int quantity,
        String discountRate,
        double price,
        String taxRate,
        int unitMeasureId,
        int standardCodeId,
        int isExcluded,
        int tributeId,
        WithholdingTaxesRequestDTO[] withholdingTaxes
) implements Serializable {
}
