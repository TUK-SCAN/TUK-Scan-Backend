package com.tookscan.tookscan.account.application.usecase;

import com.tookscan.tookscan.account.application.dto.response.ReadUserSummaryResponseDto;
import com.tookscan.tookscan.core.annotation.bean.UseCase;

import java.util.UUID;

@UseCase
public interface ReadUserSummaryUseCase {
    /**
     * 사용자 요약 정보 조회 유스케이스
     * @param accountId UUID
     * @return ReadUserSummaryResponseDto
     */
    ReadUserSummaryResponseDto execute(UUID accountId);
}
