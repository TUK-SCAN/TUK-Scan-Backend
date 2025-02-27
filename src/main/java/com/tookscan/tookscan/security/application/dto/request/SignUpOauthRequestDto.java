package com.tookscan.tookscan.security.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record SignUpOauthRequestDto(

        @JsonProperty("name")
        @NotBlank(message = "이름을 입력해주세요.")
        String name,

        @JsonProperty("phone_number")
        @NotBlank(message = "전화번호를 입력해주세요.")
        @Pattern(
                regexp = "^\\d{10,11}$",
                message = "전화번호 형식이 올바르지 않습니다. (- 없이 입력해주세요, 예: 01012345678)"
        )
        String phoneNumber,

        @JsonProperty("is_receive_email")
        @NotNull(message = "이메일 수신 동의 여부를 선택해주세요.")
        Boolean isReceiveEmail,

        @JsonProperty("is_receive_sms")
        @NotNull(message = "SMS 수신 동의 여부를 선택해주세요.")
        Boolean isReceiveSms
) {
}
