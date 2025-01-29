package com.tookscan.tookscan.account.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tookscan.tookscan.account.domain.User;
import com.tookscan.tookscan.address.domain.Address;
import com.tookscan.tookscan.core.dto.SelfValidating;
import com.tookscan.tookscan.security.domain.type.ESecurityProvider;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReadAdminUserDetailResponseDto extends SelfValidating<ReadAdminUserDetailResponseDto> {

    @JsonProperty("serial_id")
    @Schema(description = "시리얼 ID", example = "user123")
    private final String serialId;

    @JsonProperty("name")
    @Schema(description = "이름", example = "홍길동")
    @NotNull
    private final String name;

    @JsonProperty("provider")
    @Schema(description = "제공자", example = "KAKAO | GOOGLE | DEFAULT")
    @NotNull
    private final ESecurityProvider provider;

    @JsonProperty("phone_number")
    @Schema(description = "전화번호", example = "01012345678")
    @NotNull
    private final String phoneNumber;

    @JsonProperty("email")
    @Schema(description = "이메일", example = "user@example.com")
    private final String email;

    @JsonProperty("address")
    @Schema(description = "주소")
    private final AddressDto address;

    @JsonProperty("memo")
    @Schema(description = "메모", example = "VIP 고객")
    private final String memo;

    @Builder
    public ReadAdminUserDetailResponseDto(String serialId, String name, ESecurityProvider provider, String phoneNumber, String email, AddressDto address, String memo) {
        this.serialId = serialId;
        this.name = name;
        this.provider = provider;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.memo = memo;
        this.validateSelf();
    }

    @Getter
    public static class AddressDto {

        @JsonProperty("address_name")
        @Schema(description = "주소명", example = "서울시 강남구 역삼동 역삼로1길")
        @NotNull
        private final String addressName;

        @JsonProperty("region_1depth_name")
        @Schema(description = "1차 지역명", example = "서울시")
        @NotNull
        private final String region1DepthName;

        @JsonProperty("region_2depth_name")
        @Schema(description = "2차 지역명", example = "강남구")
        @NotNull
        private final String region2DepthName;

        @JsonProperty("region_3depth_name")
        @Schema(description = "3차 지역명", example = "역삼동")
        @NotNull
        private final String region3DepthName;

        @JsonProperty("region_4depth_name")
        @Schema(description = "4차 지역명", example = "역삼로1길")
        private final String region4DepthName;

        @JsonProperty("address_detail")
        @Schema(description = "상세 주소", example = "3층 301호")
        @NotNull
        private final String addressDetail;

        @JsonProperty("longitude")
        @Schema(description = "경도", example = "127.123456")
        @NotNull
        private final Double longitude;

        @JsonProperty("latitude")
        @Schema(description = "위도", example = "37.123456")
        @NotNull
        private final Double latitude;

        @Builder
        public AddressDto(String addressName, String region1DepthName, String region2DepthName, String region3DepthName, String region4DepthName, String addressDetail, Double longitude, Double latitude) {
            this.addressName = addressName;
            this.region1DepthName = region1DepthName;
            this.region2DepthName = region2DepthName;
            this.region3DepthName = region3DepthName;
            this.region4DepthName = region4DepthName;
            this.addressDetail = addressDetail;
            this.longitude = longitude;
            this.latitude = latitude;
        }

        public static AddressDto fromEntity(Address address) {
            return AddressDto.builder()
                    .addressName(address.getAddressName())
                    .region1DepthName(address.getRegion1DepthName())
                    .region2DepthName(address.getRegion2DepthName())
                    .region3DepthName(address.getRegion3DepthName())
                    .region4DepthName(address.getRegion4DepthName())
                    .addressDetail(address.getAddressDetail())
                    .longitude(address.getLongitude())
                    .latitude(address.getLatitude())
                    .build();
        }
    }

    public static ReadAdminUserDetailResponseDto fromEntity(User user) {
        return ReadAdminUserDetailResponseDto.builder()
                .serialId(user.getSerialId())
                .name(user.getName())
                .provider(user.getProvider())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail() != null ? user.getEmail() : null)
                .address(AddressDto.fromEntity(user.getAddress()) != null ? AddressDto.fromEntity(user.getAddress()) : null)
                .memo(user.getMemo() != null ? user.getMemo() : null)
                .build();
    }
}
