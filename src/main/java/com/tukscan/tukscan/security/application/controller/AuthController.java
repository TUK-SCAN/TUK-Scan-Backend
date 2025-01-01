package com.tukscan.tukscan.security.application.controller;

import com.tukscan.tukscan.core.annotation.security.AccountID;
import com.tukscan.tukscan.core.constant.Constants;
import com.tukscan.tukscan.core.dto.ResponseDto;
import com.tukscan.tukscan.core.exception.error.ErrorCode;
import com.tukscan.tukscan.core.exception.type.CommonException;
import com.tukscan.tukscan.core.utility.HeaderUtil;
import com.tukscan.tukscan.security.application.dto.request.*;
import com.tukscan.tukscan.security.application.dto.response.*;
import com.tukscan.tukscan.security.application.usecase.*;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Hidden
@RequestMapping("/v1")
public class AuthController {

    private final ReissueJsonWebTokenUseCase reissueJsonWebTokenUseCase;
    private final IssueAuthenticationCodeUseCase issueAuthenticationCodeUseCase;
    private final SignUpDefaultUseCase signUpDefaultUseCase;
    private final AdminSignUpDefaultUseCase adminSignUpDefaultUseCase;
    private final SignUpOauthUseCase signUpOauthUseCase;
    private final ReadSerialIdAndProviderUseCase readSerialIdAndProviderUseCase;
    private final ValidateIdUseCase validateIdUseCase;
    private final ValidateAuthenticationCodeUseCase validateAuthenticationCodeUseCase;
    private final ReissuePasswordUseCase reissuePasswordUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;

    /**
     * 2.1 JWT 재발급
     */
    @PostMapping("/auth/reissue/token")
    public ResponseDto<DefaultJsonWebTokenDto> reissueDefaultJsonWebToken(
            HttpServletRequest request
    ) {
        String refreshToken = HeaderUtil.refineHeader(request, Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX)
                .orElseThrow(() -> new CommonException(ErrorCode.INVALID_HEADER_ERROR));

        return ResponseDto.created(reissueJsonWebTokenUseCase.execute(refreshToken));
    }

    /**
     * 2.2 휴대폰 인증번호 발송
     */
    @PostMapping("/auth/authentication-code")
    public ResponseDto<IssueAuthenticationCodeResponseDto> issueAuthenticationCode(
            IssueAuthenticationCodeRequestDto requestDto
    ) {
        return ResponseDto.created(issueAuthenticationCodeUseCase.execute(requestDto));
    }

    /**
     * 2.3 유저 회원가입
     */
    @PostMapping("/users/auth/sign-up")
    public ResponseDto<Void> signUpDefault(
            SignUpDefaultRequestDto requestDto
    ) {
        signUpDefaultUseCase.execute(requestDto);
        return ResponseDto.created(null);
    }

    /**
     * 2.4 관리자 회원가입
     */
    @PostMapping("/admins/auth/sign-up")
    public ResponseDto<Void> adminSignUpDefault(
            AdminSignUpDefaultRequestDto requestDto
    ) {
        adminSignUpDefaultUseCase.execute(requestDto);
        return ResponseDto.created(null);
    }

    /**
     * 2.5 소셜 회원가입
     */
    @PostMapping("/users/oauth/sign-up")
    public ResponseDto<Void> signUpOauth(
            SignUpOauthRequestDto requestDto,
            HttpServletRequest request
    ) {
        String temporaryToken = HeaderUtil.refineHeader(request, Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX)
                .orElseThrow(() -> new CommonException(ErrorCode.INVALID_HEADER_ERROR));
        signUpOauthUseCase.execute(temporaryToken, requestDto);
        return ResponseDto.created(null);
    }

    /**
     * 2.6 아이디 찾기
     */
    @PostMapping("/users/auth/serial-id")
    public ResponseDto<ReadSerialIdAndProviderResponseDto> readSerialId(
            ReadSerialIdAndProviderRequestDto requestDto
    ) {
        return ResponseDto.ok(readSerialIdAndProviderUseCase.execute(requestDto));
    }

    /**
     * 2.7 아이디 중복 검사
     */
    @GetMapping("/users/auth/validations/id?{id}")
    public ResponseDto<ValidationResponseDto> validateId(
            @RequestParam(name = "id") String id
    ) {
        return ResponseDto.ok(validateIdUseCase.execute(id));
    }

    /**
     * 2.8 인증번호 검사
     */
    @PatchMapping("/users/auth/authentication-code")
    public ResponseDto<Void> validateAuthenticationCode(
            ValidateAuthenticationCodeRequestDto requestDto
    ) {
        validateAuthenticationCodeUseCase.execute(requestDto);
        return ResponseDto.ok(null);
    }

    /**
     * 2.9 임시 비밀번호 발급
     */
    @PatchMapping("/users/auth/reissue/password")
    public ResponseDto<ReissuePasswordResponseDto> reissuePassword(
            ReissuePasswordRequestDto requestDto
    ) {
        return ResponseDto.ok(reissuePasswordUseCase.execute(requestDto));
    }

    /**
     * 2.10 비밀번호 변경
     */
    @PatchMapping("/auth/password")
    public ResponseDto<Void> changePassword(
            @AccountID UUID accountId,
            ChangePasswordRequestDto requestDto
    ) {
        changePasswordUseCase.execute(accountId, requestDto);
        return ResponseDto.ok(null);
    }

    /**
     * 2.11 회원 탈퇴
     */
    @DeleteMapping("/users/auth")
    public ResponseDto<Void> deleteAccount(
            @AccountID UUID accountId
    ) {
        deleteAccountUseCase.execute(accountId);
        return ResponseDto.ok(null);
    }
}
