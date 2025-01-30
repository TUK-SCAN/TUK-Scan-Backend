package com.tookscan.tookscan.account.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record DeleteAdminUserRequestDto(
        @JsonProperty("user_ids")
        @NotNull(message = "user_ids는 필수값입니다.")
        List<UUID> userIds
) {
}
