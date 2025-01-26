package com.tookscan.tookscan.security.application.usecase;

import com.tookscan.tookscan.security.application.dto.response.ReadUserBriefResponseDto;
import com.tookscan.tookscan.core.annotation.bean.UseCase;

import java.util.UUID;

@UseCase
public interface ReadUserBriefUseCase {
    /**
     * 사용자 간단 정보 조회 유스케이스
     * @param accountId UUID
     * @return ReadAccountBriefResponseDto
     */
    ReadUserBriefResponseDto execute(UUID accountId);
}
