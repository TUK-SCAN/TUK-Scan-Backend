package com.tookscan.tookscan.payment.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreatePaymentRequestDto(
        @JsonProperty("order_id")
        Long orderId
) {
}
