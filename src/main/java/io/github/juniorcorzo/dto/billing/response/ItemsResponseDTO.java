package io.github.juniorcorzo.dto.billing.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.juniorcorzo.dto.measurement.MeasurementUnitsDTO;
import io.github.juniorcorzo.dto.tributes.TributesDTO;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ItemsResponseDTO(
        String codeReference,
        String name,
        int quantity,
        String discountRate,
        String discount,
        String grossValue,
        String taxRate,
        String taxableAmount,
        String taxAmount,
        String price,
        byte isExclude,
        MeasurementUnitsDTO unitMeasure,
        StandardCodeDTO standardCode,
        TributesDTO tribute,
        double total,
        WithholdingTaxesResponseDTO[] withholdingTaxes
) {
}
