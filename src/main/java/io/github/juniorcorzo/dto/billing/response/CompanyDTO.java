package io.github.juniorcorzo.dto.billing.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CompanyDTO(
        String urlLogo,
        String nit,
        String dv,
        String company,
        String name,
        String registrationCode,
        String economicActivity,
        String phone,
        String email,
        String direction,
        String municipality
) {
}
