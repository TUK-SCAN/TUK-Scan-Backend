package com.tookscan.tookscan.security.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tookscan.tookscan.core.dto.SelfValidating;
import com.tookscan.tookscan.security.domain.mysql.Account;
import com.tookscan.tookscan.security.domain.type.ESecurityRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReadUserBriefResponseDto extends SelfValidating<ReadUserBriefResponseDto> {

    @JsonProperty("account_type")
    @Schema(description = "계정 유형", example = "ADMIN | USER")
    @NotNull(message = "계정 유형은 필수입니다")
    private ESecurityRole accountType;

    @JsonProperty("name")
    @Schema(description = "이름", example = "홍길동")
    @NotNull(message = "이름은 필수입니다")
    private String name;

    @Builder
    public ReadUserBriefResponseDto(ESecurityRole accountType, String name) {
        this.accountType = accountType;
        this.name = name;
        this.validateSelf();
    }

    public static ReadUserBriefResponseDto fromEntity(Account account) {
        return ReadUserBriefResponseDto.builder()
                .accountType(account.getRole())
                .name(account.getName())
                .build();
    }
}
