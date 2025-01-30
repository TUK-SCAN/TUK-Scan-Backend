package com.tookscan.tookscan.account.application.usecase;

import com.tookscan.tookscan.core.annotation.bean.UseCase;

@UseCase
public interface DeleteAdminGroupUseCase {

    /**
     * 3.5.1 (관리자) 그룹 삭제
     */
    void execute(Long groupId);
}
