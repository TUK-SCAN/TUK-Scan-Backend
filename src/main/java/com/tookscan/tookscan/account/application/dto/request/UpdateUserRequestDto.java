package com.tookscan.tookscan.account.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tookscan.tookscan.address.dto.request.AddressRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UpdateUserRequestDto(
        @JsonProperty("phone_number")
        @Schema(description = "휴대폰 번호", example = "01012345678")
        @NotNull(message = "휴대폰 번호는 필수입니다.")
        @Pattern(
                regexp = "^\\d{10,11}$",
                message = "전화번호 형식이 올바르지 않습니다. (- 없이 입력해주세요, 예: 01012345678)"
        )
        String phoneNumber,

        @JsonProperty("email")
        @Schema(description = "이메일", example = "gildong123@gmail.com")
        @Pattern(
                regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
                message = "이메일 형식이 올바르지 않습니다."
        )
        String email,

        @JsonProperty("address")
        @Schema(description = "주소")
        AddressRequestDto address
) {
}
