package com.tookscan.tookscan.account.application.usecase;

import com.tookscan.tookscan.account.application.dto.request.DeleteAdminUserRequestDto;
import com.tookscan.tookscan.core.annotation.bean.UseCase;

import java.util.UUID;

@UseCase
public interface DeleteAdminUserUseCase {
    /**
     * 3.5.2 (관리자) 유저 삭제
     */
    void execute(DeleteAdminUserRequestDto requestDto);
}
