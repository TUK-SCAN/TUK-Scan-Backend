package com.tukscan.tukscan.security.application.usecase;

import com.tukscan.tukscan.core.annotation.bean.UseCase;
import com.tukscan.tukscan.security.application.dto.request.ValidateAuthenticationCodeRequestDto;

@UseCase
public interface ValidateAuthenticationCodeUseCase {

    /**
     * 전화번호 인증 코드 검증
     * @param requestDto 전화번호 인증 요청 DTO
     */
    void execute(ValidateAuthenticationCodeRequestDto requestDto);
}
