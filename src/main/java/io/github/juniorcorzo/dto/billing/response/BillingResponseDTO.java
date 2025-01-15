package io.github.juniorcorzo.dto.billing.response;

import io.github.juniorcorzo.dto.numberingRange.NumberingRangeDTO;

public record BillingResponseDTO(
        CompanyDTO company,
        CustomerResponseDTO customer,
        NumberingRangeDTO numberingRange,
        BillingInfoDTO bill,
        ItemsResponseDTO items,
        WithholdingTaxesResponseDTO withholdingTaxes
) {
}
