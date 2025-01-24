package com.tookscan.tookscan.account.application.usecase;

import com.tookscan.tookscan.account.application.dto.response.ReadAdminUserOverviewResponseDto;
import com.tookscan.tookscan.core.annotation.bean.UseCase;

import java.time.LocalDate;
import java.util.UUID;

@UseCase
public interface ReadAdminUserOverviewUseCase {
    /**
     * 관리자 유저 리스트 조회 유스케이스
     */
    ReadAdminUserOverviewResponseDto execute(UUID accountId, String search, Long groupId, LocalDate startDate, LocalDate endDate, Integer page, Integer size);
}
