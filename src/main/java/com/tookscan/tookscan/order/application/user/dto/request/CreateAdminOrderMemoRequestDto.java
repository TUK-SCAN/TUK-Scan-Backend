package com.tookscan.tookscan.order.application.user.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record CreateAdminOrderMemoRequestDto(
    @JsonProperty("content")
    @NotNull
    String content
) {
}