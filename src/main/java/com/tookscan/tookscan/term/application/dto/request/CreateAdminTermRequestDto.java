package com.tookscan.tookscan.term.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAdminTermRequestDto(

        @JsonProperty("type")
        @NotBlank(message = "type은 필수입니다.")
        String type,

        @JsonProperty("title")
        @NotBlank(message = "title은 필수입니다.")
        String title,

        @JsonProperty("content")
        @NotBlank(message = "content는 필수입니다.")
        String content,

        @JsonProperty("is_required")
        @NotNull(message = "is_required는 필수입니다.")
        Boolean isRequired
) {
}
