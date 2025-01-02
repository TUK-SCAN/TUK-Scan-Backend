package com.tookscan.tookscan.security.application.service;

import com.tookscan.tookscan.account.domain.User;
import com.tookscan.tookscan.account.domain.service.UserService;
import com.tookscan.tookscan.account.repository.mysql.UserRepository;
import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import com.tookscan.tookscan.security.application.dto.request.SignUpDefaultRequestDto;
import com.tookscan.tookscan.security.application.usecase.SignUpDefaultUseCase;
import com.tookscan.tookscan.security.domain.redis.AuthenticationCode;
import com.tookscan.tookscan.security.domain.service.AuthenticationCodeService;
import com.tookscan.tookscan.security.domain.type.ESecurityProvider;
import com.tookscan.tookscan.security.repository.mysql.AccountRepository;
import com.tookscan.tookscan.security.repository.redis.AuthenticationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignUpDefaultService implements SignUpDefaultUseCase {

    private final AuthenticationCodeRepository authenticationCodeRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    private final AuthenticationCodeService authenticationCodeService;
    private final UserService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void execute(SignUpDefaultRequestDto requestDto) {

        // 중복된 아이디인지 확인
        if (isDuplicatedId(requestDto.serialId())) {
            throw new CommonException(ErrorCode.ALREADY_EXIST_ID);
        }

        // 중복된 전화번호인지 확인
        if (isDuplicatedPhoneNumber(requestDto.phoneNumber())) {
            throw new CommonException(ErrorCode.ALREADY_EXIST_PHONE_NUMBER);
        }

        // 해당 번호에 관련된 인증번호 조회
        AuthenticationCode authenticationCode = authenticationCodeRepository.findById(requestDto.phoneNumber())
                .orElse(null);

        // 인증번호 인증이 완료되었는지 확인
        authenticationCodeService.validateAuthenticationCode(authenticationCode);

        // 유저 생성 및 저장
        User user = userService.createUser(
                ESecurityProvider.DEFAULT,
                requestDto.serialId(),
                bCryptPasswordEncoder.encode(requestDto.password()),
                requestDto.name(),
                requestDto.phoneNumber(),
                requestDto.marketingAllowed()
        );
        userRepository.save(user);
    }

    /**
     * 중복된 아이디인지 확인
     * @param serialId 아이디
     * @return 중복된 아이디인지 여부
     */
    private Boolean isDuplicatedId(String serialId) {
        return accountRepository.findBySerialIdAndProvider(serialId, ESecurityProvider.DEFAULT).isPresent();
    }

    /**
     * 중복된 전화번호인지 확인
     * @param phoneNumber 전화번호
     * @return 중복된 전화번호인지 여부
     */
    private Boolean isDuplicatedPhoneNumber(String phoneNumber) {
        return accountRepository.findByPhoneNumber(phoneNumber).isPresent();
    }
}
