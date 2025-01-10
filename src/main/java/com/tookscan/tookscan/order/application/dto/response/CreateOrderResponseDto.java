package com.tookscan.tookscan.order.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tookscan.tookscan.core.dto.SelfValidating;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateOrderResponseDto extends SelfValidating<CreateOrderResponseDto> {

    @JsonProperty("order_number")
    private final Long orderNumber;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("payment_prediction")
    private final Integer paymentPrediction;

    @JsonProperty("email")
    private final String email;

    @JsonProperty("address")
    private final String address;


    @Builder
    public CreateOrderResponseDto(Long orderNumber, String name, Integer paymentPrediction, String email, String address) {
        this.orderNumber = orderNumber;
        this.name = name;
        this.paymentPrediction = paymentPrediction;
        this.email = email;
        this.address = address;
        this.validateSelf();
    }

    public static CreateOrderResponseDto of(Long orderNumber, String name, Integer paymentPrediction, String email, String address) {
        return CreateOrderResponseDto.builder()
                .orderNumber(orderNumber)
                .name(name)
                .paymentPrediction(paymentPrediction)
                .email(email)
                .address(address)
                .build();
    }
}
