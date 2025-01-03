package com.tookscan.tookscan.security.application.usecase;

import com.tookscan.tookscan.core.annotation.bean.UseCase;
import com.tookscan.tookscan.security.application.dto.request.IssueAuthenticationCodeRequestDto;
import com.tookscan.tookscan.security.application.dto.response.IssueAuthenticationCodeResponseDto;

@UseCase
public interface IssueAuthenticationCodeUseCase {
    /**
     * 이메일 인증 코드 발급
     * @param requestDto 이메일 인증 요청 DTO
     * @return 이메일 인증 응답 DTO
     */
    IssueAuthenticationCodeResponseDto execute(IssueAuthenticationCodeRequestDto requestDto);
}
