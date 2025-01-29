package com.tookscan.tookscan.security.application.service;

import com.tookscan.tookscan.core.utility.PasswordUtil;
import com.tookscan.tookscan.security.application.dto.request.IssueAuthenticationCodeRequestDto;
import com.tookscan.tookscan.security.application.dto.response.IssueAuthenticationCodeResponseDto;
import com.tookscan.tookscan.security.application.usecase.IssueAuthenticationCodeUseCase;
import com.tookscan.tookscan.security.domain.redis.AuthenticationCode;
import com.tookscan.tookscan.security.domain.redis.AuthenticationCodeHistory;
import com.tookscan.tookscan.security.domain.service.AuthenticationCodeHistoryService;
import com.tookscan.tookscan.security.domain.service.AuthenticationCodeService;
import com.tookscan.tookscan.security.event.CompletePhoneNumberValidationEvent;
import com.tookscan.tookscan.security.repository.redis.AuthenticationCodeHistoryRepository;
import com.tookscan.tookscan.security.repository.redis.AuthenticationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IssueAuthenticationCodeService implements IssueAuthenticationCodeUseCase {
    private final AuthenticationCodeRepository authenticationCodeRepository;
    private final AuthenticationCodeHistoryRepository authenticationCodeHistoryRepository;

    private final AuthenticationCodeHistoryService authenticationCodeHistoryService;
    private final AuthenticationCodeService authenticationCodeService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Transactional
    public IssueAuthenticationCodeResponseDto execute(IssueAuthenticationCodeRequestDto requestDto) {

        // 인증코드 발급 이력 조회
        AuthenticationCodeHistory history = authenticationCodeHistoryRepository.findById(requestDto.phoneNumber())
                .orElse(null);

        // 과거에 발급된 인증코드 조회
        AuthenticationCode authenticationCode = authenticationCodeRepository.findById(requestDto.phoneNumber())
                .orElse(null);

        // 인증코드 발급 제한, 발급 속도 제한 유효성 검사
        authenticationCodeHistoryService.validateAuthenticationCodeHistory(history);

        // 새로운 인증코드 생성
        String code = PasswordUtil.generateAuthCode(6);

        // 새로운 인증코드 저장 혹은 업데이트
        authenticationCodeRepository.save(authenticationCodeService.createOrUpdateAuthenticationCode(
                authenticationCode,
                requestDto.phoneNumber(),
                bCryptPasswordEncoder.encode(code))
        );

        // 인증코드 발급 이력 업데이트
        if (history == null) {
            history = authenticationCodeHistoryRepository.save(authenticationCodeHistoryService.createAuthenticationCodeHistory(requestDto.phoneNumber()));
        } else {
            history = authenticationCodeHistoryRepository.save(authenticationCodeHistoryService.incrementAuthenticationCodeCount(history));
        }

        applicationEventPublisher.publishEvent(CompletePhoneNumberValidationEvent.of(requestDto.phoneNumber(), code));

        return IssueAuthenticationCodeResponseDto.fromEntity(history);
    }
}
