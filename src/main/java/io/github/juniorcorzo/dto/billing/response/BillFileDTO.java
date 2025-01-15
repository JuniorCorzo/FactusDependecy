package io.github.juniorcorzo.dto.billing.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record BillFileDTO(
        String fileName,
        String pdfBase64Encoded,
        String xmlBase64Encoded
) {
}
