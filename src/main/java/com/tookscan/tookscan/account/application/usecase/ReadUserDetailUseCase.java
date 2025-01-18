package com.tookscan.tookscan.account.application.usecase;

import com.tookscan.tookscan.account.application.dto.response.ReadUserDetailResponseDto;
import com.tookscan.tookscan.core.annotation.bean.UseCase;

import java.util.UUID;

@UseCase
public interface ReadUserDetailUseCase {
    /**
     * 사용자 상세 정보 조회 유스케이스
     * @param accountId UUID
     * @return ReadUserDetailResponseDto
     */
    ReadUserDetailResponseDto execute(UUID accountId);
}
