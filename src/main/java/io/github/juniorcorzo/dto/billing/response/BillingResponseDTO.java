package io.github.juniorcorzo.dto.billing.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.juniorcorzo.dto.billing.BillingPeriodDTO;
import io.github.juniorcorzo.dto.numberingRange.NumberingRangeDTO;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record BillingResponseDTO(
        CompanyDTO company,
        CustomerResponseDTO customer,
        NumberingRangeDTO numberingRange,
        List<BillingPeriodDTO> billingPeriod,
        BillingInfoDTO bill,
        List<ItemsResponseDTO> items,
        List<DocumentResponseDTO> relatedDocuments,
        List<WithholdingTaxesResponseDTO> withholdingTaxes
) {
}
