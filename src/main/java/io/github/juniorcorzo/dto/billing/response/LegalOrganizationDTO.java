package io.github.juniorcorzo.dto.billing.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record LegalOrganizationDTO(
        int id,
        String code,
        String name
) {
}
