package com.tookscan.tookscan.order.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record UpdateAdminOrderDeliveryTrackingNumberRequestDto(
        @JsonProperty("delivery_id")
        @NotNull(message = "delivery_id는 null일 수 없습니다.")
        String trackingNumber
) {
}
