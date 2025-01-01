package com.tukscan.tukscan.security.handler.login;

import com.tukscan.tukscan.core.utility.HttpServletUtil;
import com.tukscan.tukscan.security.application.dto.response.OauthJsonWebTokenDto;
import com.tukscan.tukscan.security.application.usecase.LoginOauthUseCase;
import com.tukscan.tukscan.security.info.CustomUserPrincipal;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class Oauth2SuccessHandler implements AuthenticationSuccessHandler {

    private final LoginOauthUseCase loginOauthUseCase;
    private final HttpServletUtil httpServletUtil;

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2User principal = (OAuth2User) authentication.getPrincipal();

        OauthJsonWebTokenDto oauthJsonWebTokenDto = loginOauthUseCase.execute(principal);

        httpServletUtil.onSuccessBodyResponseWithOauthJWTBody(response, oauthJsonWebTokenDto);
    }
}
