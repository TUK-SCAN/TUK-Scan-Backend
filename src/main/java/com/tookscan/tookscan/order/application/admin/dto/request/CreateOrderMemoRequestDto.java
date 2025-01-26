package com.tookscan.tookscan.order.application.admin.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record CreateOrderMemoRequestDto (
    @JsonProperty("content")
    @NotNull
    String content
) {
}