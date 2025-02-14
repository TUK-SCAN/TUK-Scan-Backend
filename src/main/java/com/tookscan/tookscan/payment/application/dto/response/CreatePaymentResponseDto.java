package com.tookscan.tookscan.payment.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tookscan.tookscan.core.dto.SelfValidating;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreatePaymentResponseDto extends SelfValidating<CreatePaymentResponseDto> {
    @JsonProperty("order_id")
    private final String orderId;

    @JsonProperty("payment_url")
    private final String paymentUrl;

    @Builder
    public CreatePaymentResponseDto(String orderId, String paymentUrl) {
        this.orderId = orderId;
        this.paymentUrl = paymentUrl;
        this.validateSelf();
    }

    public static CreatePaymentResponseDto of(String orderId, String paymentUrl) {
        return CreatePaymentResponseDto.builder()
                .orderId(orderId)
                .paymentUrl(paymentUrl)
                .build();
    }

}
