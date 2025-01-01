package com.tukscan.tukscan.security.application.usecase;

import com.tukscan.tukscan.core.annotation.bean.UseCase;
import com.tukscan.tukscan.security.application.dto.request.SignUpDefaultRequestDto;
import com.tukscan.tukscan.security.application.dto.response.IssueAuthenticationCodeResponseDto;

@UseCase
public interface SignUpDefaultUseCase {
    /**
     * 임시 회원가입 유스케이스
     * @param requestDto 점주 회원가입 요청 DTO With Token
     * @return TemporaryJsonWebTokenDto
     */
     void execute(SignUpDefaultRequestDto requestDto);
}
