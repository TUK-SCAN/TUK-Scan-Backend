package com.tukscan.tukscan.security.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tukscan.tukscan.core.dto.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OauthJsonWebTokenDto extends SelfValidating<OauthJsonWebTokenDto> {

    @JsonProperty("temporary_token")
    private final String temporaryToken;

    @JsonProperty("access_token")
    private final String accessToken;

    @JsonProperty("refresh_token")
    private final String refreshToken;

    @Builder
    public OauthJsonWebTokenDto(String temporaryToken, String accessToken, String refreshToken) {
        this.temporaryToken = temporaryToken;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.validateSelf();
    }

}
