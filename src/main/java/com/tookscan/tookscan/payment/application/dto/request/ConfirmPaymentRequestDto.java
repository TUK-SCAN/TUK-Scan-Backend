package com.tookscan.tookscan.payment.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ConfirmPaymentRequestDto(
        @JsonProperty("order_number")
        String orderNumber,
        @JsonProperty("payment_key")
        String paymentKey,
        @JsonProperty("amount")
        Integer amount
) {
}
