package io.github.juniorcorzo.dto.billing.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.juniorcorzo.dto.billing.BillingPeriodDTO;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record BillingRequestDTO(
        int numberingRangeId,
        String document,
        String referenceCode,
        String observation,
        String paymentForm,
        String paymentDueDate,
        Integer paymentMethodCode,
        BillingPeriodDTO billingPeriod,
        RelatedDocumentRequestDTO relatedDocument,
        CustomerRequestDTO customer,
        List<ItemsRequestDTO> items
) implements Serializable {

}
