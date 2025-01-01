package com.tukscan.tukscan.security.application.usecase;

import com.tukscan.tukscan.core.annotation.bean.UseCase;
import com.tukscan.tukscan.security.application.dto.request.IssueAuthenticationCodeRequestDto;
import com.tukscan.tukscan.security.application.dto.response.IssueAuthenticationCodeResponseDto;

@UseCase
public interface IssueAuthenticationCodeUseCase {
    /**
     * 이메일 인증 코드 발급
     * @param requestDto 이메일 인증 요청 DTO
     * @return 이메일 인증 응답 DTO
     */
    IssueAuthenticationCodeResponseDto execute(IssueAuthenticationCodeRequestDto requestDto);
}
