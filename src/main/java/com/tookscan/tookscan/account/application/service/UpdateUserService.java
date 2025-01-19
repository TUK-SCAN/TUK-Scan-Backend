package com.tookscan.tookscan.account.application.service;

import com.tookscan.tookscan.account.application.dto.request.UpdateUserRequestDto;
import com.tookscan.tookscan.account.application.usecase.UpdateUserUseCase;
import com.tookscan.tookscan.account.domain.User;
import com.tookscan.tookscan.account.domain.service.UserService;
import com.tookscan.tookscan.account.repository.mysql.UserRepository;
import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import com.tookscan.tookscan.security.domain.redis.AuthenticationCode;
import com.tookscan.tookscan.security.domain.service.AuthenticationCodeService;
import com.tookscan.tookscan.security.repository.redis.AuthenticationCodeHistoryRepository;
import com.tookscan.tookscan.security.repository.redis.AuthenticationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateUserService implements UpdateUserUseCase {

    private final UserRepository userRepository;
    private final AuthenticationCodeRepository authenticationCodeRepository;
    private final AuthenticationCodeHistoryRepository authenticationCodeHistoryRepository;

    private final UserService userService;
    private final AuthenticationCodeService authenticationCodeService;

    @Override
    @Transactional
    public void execute(UUID accountId, UpdateUserRequestDto requestDto) {

        // User 조회
        User user = userRepository.findById(accountId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // 휴대폰 번호가 변경되었을 경우, 인증 코드 인증 여부 확인
        if (userService.isPhoneNumberChanged(user, requestDto.phoneNumber())) {

            // 해당 번호에 관련된 인증번호 조회
            AuthenticationCode authenticationCode = authenticationCodeRepository.findById(requestDto.phoneNumber())
                    .orElse(null);

            // 인증번호 인증이 완료되었는지 확인
            authenticationCodeService.validateAuthenticationCode(authenticationCode);
        }

        // User 정보 수정
        user = userService.updateSelf(user, requestDto.email(), requestDto.phoneNumber(), requestDto.address());
        userRepository.save(user);

        // 인증번호 삭제
        authenticationCodeRepository.deleteById(requestDto.phoneNumber());

        // 인증번호 발급 이력 삭제
        authenticationCodeHistoryRepository.deleteById(requestDto.phoneNumber());
    }
}
