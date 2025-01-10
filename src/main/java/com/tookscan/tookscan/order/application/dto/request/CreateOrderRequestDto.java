package com.tookscan.tookscan.order.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tookscan.tookscan.order.domain.type.ERecoveryOption;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record CreateOrderRequestDto(

        @NotNull(message = "documents는 null일 수 없습니다.")
        @JsonProperty("documents")
        List<RequestDocument> documents,

        @NotNull(message = "delivery_info는 null일 수 없습니다.")
        @JsonProperty("delivery_info")
        DeliveryInfo deliveryInfo
) {
    public record RequestDocument(

            @NotBlank(message = "name은 null일 수 없습니다.")
            @JsonProperty("name")
            String name,

            @JsonProperty("request")
            String request,

            @NotNull(message = "page_prediction은 null일 수 없습니다.")
            @JsonProperty("page_prediction")
            Integer pagePrediction,

            @NotNull(message = "recovery_option은 null일 수 없습니다.")
            @JsonProperty("recovery_option")
            ERecoveryOption recoveryOption
    ) {
    }

    public record DeliveryInfo(

            @NotBlank(message = "receiver_name은 null일 수 없습니다.")
            @JsonProperty("receiver_name")
            String receiverName,

            @NotBlank(message = "phone_number는 null일 수 없습니다.")
            @JsonProperty("phone_number")
            String phoneNumber,

            @NotBlank(message = "email은 null일 수 없습니다.")
            @JsonProperty("email")
            String email,

            @NotBlank(message = "authentication_code는 null일 수 없습니다.")
            @JsonProperty("authentication_code")
            String authenticationCode,

            @JsonProperty("request")
            String request,

            @NotNull(message = "address는 null일 수 없습니다.")
            @JsonProperty("address")
            Address address
    ) {
        public record Address(

                @JsonProperty("address_name")
                @NotBlank(message = "주소를 입력해주세요.")
                String addressName,

                @JsonProperty("region_1depth_name")
                @NotBlank(message = "시/도를 입력해주세요.")
                String region1DepthName,

                @JsonProperty("region_2depth_name")
                @NotBlank(message = "군/구를 입력해주세요.")
                String region2DepthName,

                @JsonProperty("region_3depth_name")
                @NotBlank(message = "읍/면/동을 입력해주세요.")
                String region3DepthName,

                @JsonProperty("region_4depth_name")
                String region4DepthName,

                @JsonProperty("address_detail")
                @NotBlank(message = "상세 주소를 입력해주세요.")
                @Size(max = 50, message = "상세 주소는 50자 이내로 입력해주세요.")
                String addressDetail,

                @JsonProperty("latitude")
                @NotNull(message = "위도를 입력해주세요.")
                Double latitude,

                @JsonProperty("longitude")
                @NotNull(message = "경도를 입력해주세요.")
                Double longitude
        ) {
        }
    }
}