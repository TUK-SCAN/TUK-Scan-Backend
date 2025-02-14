package com.tookscan.tookscan.payment.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tookscan.tookscan.core.dto.SelfValidating;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ConfirmPaymentResponseDto extends SelfValidating<ConfirmPaymentResponseDto> {
    @JsonProperty("order_id")
    private final String orderId;

    @JsonProperty("status")
    private final String status;

    @JsonProperty("message")
    private final String message;

    @Builder
    public ConfirmPaymentResponseDto(String orderId, String status, String message) {
        this.orderId = orderId;
        this.status = status;
        this.message = message;
        this.validateSelf();
    }

    public static ConfirmPaymentResponseDto of(String orderId, String status, String message) {
        return ConfirmPaymentResponseDto.builder()
                .orderId(orderId)
                .status(status)
                .message(message)
                .build();
    }
}
