package com.tookscan.tookscan.term.application.usecase;

import com.tookscan.tookscan.core.annotation.bean.UseCase;

@UseCase
public interface DeleteAdminTermUseCase {

    /**
     * 8.5.1 (관리자) 약관 삭제
     */
    void execute(Long termId);
}
