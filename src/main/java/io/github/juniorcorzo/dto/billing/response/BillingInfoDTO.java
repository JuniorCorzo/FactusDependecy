package io.github.juniorcorzo.dto.billing.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record BillingInfoDTO(
        int id,
        String number,
        String referenceCode,
        byte status,
        byte sendEmail,
        String qr,
        String cufe,
        String validated,
        String discountRate,
        String discount,
        String grossValue,
        String taxableAmount,
        String taxAmount,
        String total,
        String observation,
        String[] error,
        String createAt,
        String paymentDueDate,
        String qrImage,
        PaymentResponseDTO paymentMethod
) {
}
