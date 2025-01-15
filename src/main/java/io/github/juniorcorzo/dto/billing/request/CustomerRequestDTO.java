package io.github.juniorcorzo.dto.billing.request;

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
