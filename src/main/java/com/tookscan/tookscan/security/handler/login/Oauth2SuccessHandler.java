package com.tookscan.tookscan.security.handler.login;

import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import com.tookscan.tookscan.core.utility.HttpServletUtil;
import com.tookscan.tookscan.security.application.dto.response.OauthJsonWebTokenDto;
import com.tookscan.tookscan.security.application.usecase.LoginOauthUseCase;
import com.tookscan.tookscan.security.info.CustomTemporaryUserPrincipal;
import com.tookscan.tookscan.security.info.CustomUserPrincipal;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
@Component
@RequiredArgsConstructor
@Slf4j
public class Oauth2SuccessHandler implements AuthenticationSuccessHandler {

    private final LoginOauthUseCase loginOauthUseCase;
    private final HttpServletUtil httpServletUtil;

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2User principal = (OAuth2User) authentication.getPrincipal();

        log.info("🔍 OAuth2 Authentication Success - Principal Class: {}", principal.getClass().getName());
        log.info("🔍 OAuth2 User Attributes: {}", principal.getAttributes());

        OauthJsonWebTokenDto oauthJsonWebTokenDto = null;

        if (principal instanceof CustomTemporaryUserPrincipal) {
            log.info("✅ Principal is CustomTemporaryUserPrincipal");
            oauthJsonWebTokenDto = loginOauthUseCase.execute((CustomTemporaryUserPrincipal) principal);
        } else if (principal instanceof CustomUserPrincipal) {
            log.info("✅ Principal is CustomUserPrincipal");
            oauthJsonWebTokenDto = loginOauthUseCase.execute((CustomUserPrincipal) principal);
        }
        else {
            log.error("❌ Invalid Principal Type: {}", principal.getClass().getName());
            throw new CommonException(ErrorCode.INVALID_PRINCIPAL_TYPE);
        }
        log.info("✅ OAuth2 Authentication Success - JWT Generated Successfully");

        httpServletUtil.onSuccessBodyResponseWithOauthJWTBody(response, oauthJsonWebTokenDto);
    }
}
