package com.tukscan.tukscan.security.application.usecase;

import com.tukscan.tukscan.core.annotation.bean.UseCase;

import java.util.UUID;

@UseCase
public interface DeleteAccountUseCase {

    /**
     * 계정 탈퇴
     * @param accountId 계정 ID
     */
    void execute(UUID accountId);
}
