package com.tookscan.tookscan.order.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tookscan.tookscan.core.dto.SelfValidating;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateUserOrderResponseDto extends SelfValidating<CreateUserOrderResponseDto> {

    @JsonProperty("order_number")
    @NotNull
    private final String orderNumber;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("payment_prediction")
    @Min(0)
    private final Integer paymentPrediction;

    @JsonProperty("email")
    @Email
    private final String email;

    @JsonProperty("address")
    private final String address;


    @Builder
    public CreateUserOrderResponseDto(String orderNumber, String name, Integer paymentPrediction, String email, String address) {
        this.orderNumber = orderNumber;
        this.name = name;
        this.paymentPrediction = paymentPrediction;
        this.email = email;
        this.address = address;
        this.validateSelf();
    }

    public static CreateUserOrderResponseDto of(String orderNumber, String name, Integer paymentPrediction, String email, String address) {
        return CreateUserOrderResponseDto.builder()
                .orderNumber(orderNumber)
                .name(name)
                .paymentPrediction(paymentPrediction)
                .email(email)
                .address(address)
                .build();
    }
}
