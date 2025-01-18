package com.tookscan.tookscan.account.application.usecase;

import com.tookscan.tookscan.account.application.dto.request.UpdateUserRequestDto;
import com.tookscan.tookscan.core.annotation.bean.UseCase;

import java.util.UUID;

@UseCase
public interface UpdateUserUseCase {
    /**
     * 유저 정보 수정 유스케이스
     * @param accountId UUID
     * @param requestDto UpdateUserRequestDto
     */
    void execute(UUID accountId, UpdateUserRequestDto requestDto);
}
