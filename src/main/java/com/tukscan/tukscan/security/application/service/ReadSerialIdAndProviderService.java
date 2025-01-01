package com.tukscan.tukscan.security.application.service;

import com.tukscan.tukscan.account.repository.mysql.UserRepository;
import com.tukscan.tukscan.core.exception.error.ErrorCode;
import com.tukscan.tukscan.core.exception.type.CommonException;
import com.tukscan.tukscan.security.application.dto.request.ReadSerialIdAndProviderRequestDto;
import com.tukscan.tukscan.security.application.dto.response.ReadSerialIdAndProviderResponseDto;
import com.tukscan.tukscan.security.application.usecase.ReadSerialIdAndProviderUseCase;
import com.tukscan.tukscan.security.domain.mysql.Account;
import com.tukscan.tukscan.security.domain.redis.AuthenticationCode;
import com.tukscan.tukscan.security.domain.service.AuthenticationCodeService;
import com.tukscan.tukscan.security.repository.mysql.AccountRepository;
import com.tukscan.tukscan.security.repository.redis.AuthenticationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReadSerialIdAndProviderService implements ReadSerialIdAndProviderUseCase {

    private final UserRepository userRepository;
    private final AuthenticationCodeRepository authenticationCodeRepository;

    private final AuthenticationCodeService authenticationCodeService;

    @Override
    @Transactional(readOnly = true)
    public ReadSerialIdAndProviderResponseDto execute(ReadSerialIdAndProviderRequestDto requestDto) {

        // 인증 코드 조회
        AuthenticationCode authenticationCode = authenticationCodeRepository.findById(requestDto.phoneNumber())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // 인증 코드 검증 확인
        authenticationCodeService.validateAuthenticationCode(authenticationCode);

        // 계정 조회
        Account account = (Account) userRepository.findByPhoneNumberAndName(requestDto.phoneNumber(), requestDto.name())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        return ReadSerialIdAndProviderResponseDto.fromEntity(account);
    }
}
