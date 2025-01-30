package com.tookscan.tookscan.order.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tookscan.tookscan.address.dto.request.AddressRequestDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UpdateAdminOrderDeliveryRequestDto(

        @JsonProperty("receiver_name")
        @NotBlank(message = "receiver_name은 null일 수 없습니다.")
        String receiverName,

        @JsonProperty("phone_number")
        @NotBlank(message = "phone_number는 null일 수 없습니다.")
        @Pattern(
                regexp = "^\\d{10,11}$",
                message = "전화번호 형식이 올바르지 않습니다. (- 없이 입력해주세요, 예: 01012345678)"
        )
        String phoneNumber,

        @JsonProperty("address")
        @NotNull(message = "address는 null일 수 없습니다.")
        AddressRequestDto address,

        @JsonProperty("request")
        String request,

        @JsonProperty("tracking_number")
        String trackingNumber,

        @JsonProperty("email")
        String email
) {
}
