package com.tukscan.tukscan.security.application.usecase;

import com.tukscan.tukscan.core.annotation.bean.UseCase;
import com.tukscan.tukscan.security.application.dto.request.ChangePasswordRequestDto;

import java.util.UUID;

@UseCase
public interface ChangePasswordUseCase {

    /**
     * 비밀번호 변경
     * @param accountId 계정 ID
     * @param requestDto 비밀번호 변경 요청 DTO
     */
    void execute(UUID accountId, ChangePasswordRequestDto requestDto);
}