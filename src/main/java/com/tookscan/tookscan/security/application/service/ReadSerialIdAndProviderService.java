package com.tookscan.tookscan.security.application.service;

import com.tookscan.tookscan.account.repository.mysql.UserRepository;
import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import com.tookscan.tookscan.security.application.dto.request.ReadSerialIdAndProviderRequestDto;
import com.tookscan.tookscan.security.application.dto.response.ReadSerialIdAndProviderResponseDto;
import com.tookscan.tookscan.security.application.usecase.ReadSerialIdAndProviderUseCase;
import com.tookscan.tookscan.security.domain.mysql.Account;
import com.tookscan.tookscan.security.domain.redis.AuthenticationCode;
import com.tookscan.tookscan.security.domain.service.AuthenticationCodeService;
import com.tookscan.tookscan.security.repository.redis.AuthenticationCodeRepository;
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
