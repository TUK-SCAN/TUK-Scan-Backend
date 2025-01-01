package com.tukscan.tukscan.security.application.service;

import com.tukscan.tukscan.core.exception.error.ErrorCode;
import com.tukscan.tukscan.core.exception.type.CommonException;
import com.tukscan.tukscan.core.utility.JsonWebTokenUtil;
import com.tukscan.tukscan.security.application.dto.response.OauthJsonWebTokenDto;
import com.tukscan.tukscan.security.application.usecase.LoginOauthUseCase;
import com.tukscan.tukscan.security.domain.mysql.Account;
import com.tukscan.tukscan.security.domain.service.RefreshTokenService;
import com.tukscan.tukscan.security.info.CustomTemporaryUserPrincipal;
import com.tukscan.tukscan.security.info.CustomUserPrincipal;
import com.tukscan.tukscan.security.repository.mysql.AccountRepository;
import com.tukscan.tukscan.security.repository.redis.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginOauthService implements LoginOauthUseCase {

    private final AccountRepository accountRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private final RefreshTokenService refreshTokenService;

    private final JsonWebTokenUtil jsonWebTokenUtil;

    @Override
    public OauthJsonWebTokenDto execute(OAuth2User oAuth2User) {

        // 임시 유저라면 CustomTemporaryUserPrincipal를 이용하여 Temporary Json Web Token 생성 및 반환
        if (oAuth2User instanceof CustomTemporaryUserPrincipal) {
            return jsonWebTokenUtil.generateOauthJsonWebTokens(
                    (
                            (CustomTemporaryUserPrincipal) oAuth2User).getSerialId() + ":" + ((CustomTemporaryUserPrincipal) oAuth2User).getProvider()
            );
        }

        // 임시유저가 아니라면 Account 조회
        Account account = accountRepository.findById(((CustomUserPrincipal) oAuth2User).getId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // Account 정보를 이용하여 Oauth Json Web Token 생성
        OauthJsonWebTokenDto jsonWebTokenDto = jsonWebTokenUtil.generateOauthJsonWebTokens(
                account.getId(),
                account.getRole()
        );

        String refreshToken = jsonWebTokenDto.getRefreshToken();

        // Refresh Token Redis 에 저장
        if (refreshToken != null) {
            refreshTokenRepository.save(refreshTokenService.createRefreshToken(((CustomUserPrincipal) oAuth2User).getId(), refreshToken));
        }

        return jsonWebTokenDto;
    }

}
