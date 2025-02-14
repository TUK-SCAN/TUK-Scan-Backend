package com.tookscan.tookscan.payment.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ConfirmPaymentRequestDto(
        @JsonProperty("order_id")
        String orderId,
        @JsonProperty("payment_key")
        String paymentKey,
        @JsonProperty("amount")
        Integer amount
) {
}
