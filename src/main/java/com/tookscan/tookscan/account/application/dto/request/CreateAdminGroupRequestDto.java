package com.tookscan.tookscan.account.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAdminGroupRequestDto(
        @JsonProperty("name")
        @NotBlank(message = "이름은 필수입니다")
        String name
) {
}
