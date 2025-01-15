package io.github.juniorcorzo.dto.billing.response;

import io.github.juniorcorzo.dto.PaginationDTO;

import java.util.List;

public record BillsFilteredResponseDTO(
        List<BillingResponseDTO> data,
        PaginationDTO pagination
) {
}
