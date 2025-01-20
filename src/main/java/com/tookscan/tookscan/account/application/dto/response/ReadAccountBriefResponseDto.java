package com.tookscan.tookscan.account.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tookscan.tookscan.core.dto.SelfValidating;
import com.tookscan.tookscan.security.domain.mysql.Account;
import com.tookscan.tookscan.security.domain.type.ESecurityRole;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReadAccountBriefResponseDto extends SelfValidating<ReadAccountBriefResponseDto> {

    @JsonProperty("account_type")
    @NotNull(message = "계정 유형은 필수입니다")
    private ESecurityRole accountType;

    @JsonProperty("name")
    @NotNull(message = "이름은 필수입니다")
    private String name;

    @Builder
    public ReadAccountBriefResponseDto(ESecurityRole accountType, String name) {
        this.accountType = accountType;
        this.name = name;
        this.validateSelf();
    }

    public static ReadAccountBriefResponseDto fromEntity(Account account) {
        return ReadAccountBriefResponseDto.builder()
                .accountType(account.getRole() != null ? account.getRole() : null)
                .name(account.getName() != null ? account.getName() : null)
                .build();
    }
}
