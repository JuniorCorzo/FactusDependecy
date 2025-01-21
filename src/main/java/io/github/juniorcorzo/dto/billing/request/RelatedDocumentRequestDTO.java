package io.github.juniorcorzo.dto.billing.request;

public record RelatedDocumentRequestDTO(
        String code,
        String issueDate,
        String number
) {
}
