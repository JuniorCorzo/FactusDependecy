package io.github.juniorcorzo.dto.billing.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public record BillingRequestDTO(
    int numberingRangeId,
    String referenceCode,
    String observation,
    String paymentForm,
    String paymentDueDate,
    CustomerRequestDTO customer,
    List<ItemsRequestDTO> items
) implements Serializable {
}
