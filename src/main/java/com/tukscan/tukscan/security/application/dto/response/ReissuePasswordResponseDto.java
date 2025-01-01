package com.tukscan.tukscan.security.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tukscan.tukscan.core.dto.SelfValidating;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReissuePasswordResponseDto extends SelfValidating<ReissuePasswordResponseDto> {

    @JsonProperty("temporary_password")
    private final String temporaryPassword;

    @Builder
    public ReissuePasswordResponseDto(String temporaryPassword) {
        this.temporaryPassword = temporaryPassword;
        this.validateSelf();
    }

    public static ReissuePasswordResponseDto of(String temporaryPassword) {
        return ReissuePasswordResponseDto.builder()
                .temporaryPassword(temporaryPassword)
                .build();
    }
}
