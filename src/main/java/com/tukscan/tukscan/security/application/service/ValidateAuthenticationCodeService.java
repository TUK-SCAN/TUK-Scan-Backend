package com.tukscan.tukscan.security.application.service;

import com.tukscan.tukscan.core.exception.error.ErrorCode;
import com.tukscan.tukscan.core.exception.type.CommonException;
import com.tukscan.tukscan.security.application.dto.request.ValidateAuthenticationCodeRequestDto;
import com.tukscan.tukscan.security.application.usecase.ValidateAuthenticationCodeUseCase;
import com.tukscan.tukscan.security.domain.redis.AuthenticationCode;
import com.tukscan.tukscan.security.repository.redis.AuthenticationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ValidateAuthenticationCodeService implements ValidateAuthenticationCodeUseCase {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationCodeRepository authenticationCodeRepository;

    @Override
    @Transactional
    public void execute(ValidateAuthenticationCodeRequestDto requestDto) {

        // 해당 전화번호로 발급된 인증코드 조회
        AuthenticationCode authenticationCode = authenticationCodeRepository.findById(requestDto.phoneNumber())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // 인증코드 일치 여부 확인
        if (!bCryptPasswordEncoder.matches(requestDto.authenticationCode(), authenticationCode.getValue())) {
            throw new CommonException(ErrorCode.ACCESS_DENIED);
        }

        // 인증코드 검증 여부 변경
        authenticationCode.verify();
    }
}
