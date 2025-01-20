package io.github.juniorcorzo.dto.billing.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.juniorcorzo.dto.municipality.MunicipalityDTO;
import io.github.juniorcorzo.dto.tributes.TributesDTO;

import java.io.Serializable;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CustomerResponseDTO(
        String identification,
        String dv,
        String graphicRepresentationName,
        String company,
        String tradeName,
        String names,
        String address,
        String email,
        String phone,
        LegalOrganizationDTO legalOrganization,
        TributesDTO tribute,
        MunicipalityDTO municipality
) implements Serializable {
}
