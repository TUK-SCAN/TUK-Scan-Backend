package com.tookscan.tookscan.order.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tookscan.tookscan.core.dto.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateGuestOrderResponseDto extends SelfValidating<CreateGuestOrderResponseDto> {
    @JsonProperty("order_number")
    @NotNull
    private String orderNumber;

    @Builder
    public CreateGuestOrderResponseDto(String orderNumber) {
        this.orderNumber = orderNumber;
        this.validateSelf();
    }
}
