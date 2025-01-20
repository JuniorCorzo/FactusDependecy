package io.github.juniorcorzo.dto.billing.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record BillFileDTO(
        String fileName,
        @JsonProperty("pdf_base_64_encoded")
        String pdfBase64Encoded,
        @JsonProperty("xml_base_64_encoded")
        String xmlBase64Encoded
) {

}
