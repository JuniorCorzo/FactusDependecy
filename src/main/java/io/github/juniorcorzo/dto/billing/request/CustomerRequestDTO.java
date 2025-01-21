package io.github.juniorcorzo.dto.billing.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CustomerRequestDTO(
        String identification,
        String dv,
        String company,
        String tradeName,
        String names,
        String address,
        String email,
        String phone,
        String legalOrganizationId,
        String tributeId,
        String identificationDocumentId,
        String municipalityId
)  {
}
