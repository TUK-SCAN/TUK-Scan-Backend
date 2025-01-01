package com.tukscan.tukscan.security.application.service;

import com.tukscan.tukscan.core.exception.error.ErrorCode;
import com.tukscan.tukscan.core.exception.type.CommonException;
import com.tukscan.tukscan.security.application.dto.request.ReissuePasswordRequestDto;
import com.tukscan.tukscan.security.application.dto.response.ReissuePasswordResponseDto;
import com.tukscan.tukscan.security.application.usecase.ReissuePasswordUseCase;
import com.tukscan.tukscan.security.domain.mysql.Account;
import com.tukscan.tukscan.security.domain.redis.AuthenticationCode;
import com.tukscan.tukscan.security.domain.service.AccountService;
import com.tukscan.tukscan.security.domain.service.AuthenticationCodeService;
import com.tukscan.tukscan.security.repository.mysql.AccountRepository;
import com.tukscan.tukscan.security.repository.redis.AuthenticationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class ReissuePasswordService implements ReissuePasswordUseCase {

    private final AccountRepository accountRepository;
    private final AuthenticationCodeRepository authenticationCodeRepository;

    private final AuthenticationCodeService authenticationCodeService;
    private final AccountService accountService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final String CHAR_POOL = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 8;

    @Override
    @Transactional
    public ReissuePasswordResponseDto execute(ReissuePasswordRequestDto requestDto) {

        // 인증 코드 조회
        AuthenticationCode authenticationCode = authenticationCodeRepository.findById(requestDto.phoneNumber())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // 인증 코드 검증 확인
        authenticationCodeService.validateAuthenticationCode(authenticationCode);

        // 계정 조회
        Account account = accountRepository.findByPhoneNumberAndSerialId(requestDto.phoneNumber(), requestDto.serialId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // 비밀번호 재발급 (랜덤한 숫자 + 영소문자 8자)
        String newPassword = generateRandomPassword();
        String encodedPassword = bCryptPasswordEncoder.encode(newPassword);

        // 비밀번호 변경
        accountService.changePassword(account, encodedPassword);

        return ReissuePasswordResponseDto.of(newPassword);
    }

    // 비밀번호 재발급을 위한 랜덤한 비밀번호 생성 메소드
    private String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(CHAR_POOL.length());
            password.append(CHAR_POOL.charAt(index));
        }

        return password.toString();
    }
}
