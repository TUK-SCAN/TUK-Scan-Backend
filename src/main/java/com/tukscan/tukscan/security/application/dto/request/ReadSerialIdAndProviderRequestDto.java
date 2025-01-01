package com.tukscan.tukscan.security.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ReadSerialIdAndProviderRequestDto(

        @JsonProperty("name")
        @NotBlank(message = "이름을 입력해주세요.")
        @Pattern(regexp = "^[가-힣]{2,10}$", message = "이름은 2글자 이상 10자 이하의 한글로 입력해주세요.")
        String name,

        @JsonProperty("phone_number")
        @NotBlank(message = "전화번호를 입력해주세요.")
        @Pattern(
                regexp = "^\\d{10,11}$",
                message = "전화번호 형식이 올바르지 않습니다. (- 없이 입력해주세요, 예: 01012345678)"
        )
        String phoneNumber

) {
}
