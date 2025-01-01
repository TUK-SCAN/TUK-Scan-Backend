package com.tukscan.tukscan.security.application.usecase;

import com.tukscan.tukscan.core.annotation.bean.UseCase;
import com.tukscan.tukscan.security.application.dto.response.OauthJsonWebTokenDto;
import com.tukscan.tukscan.security.info.CustomUserPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

@UseCase
public interface LoginOauthUseCase {
    /**
     * Security에서 사용되는 Social Login 유스케이스
     * @param oauth2User OAuth2User
     */

    OauthJsonWebTokenDto execute(OAuth2User oauth2User);
}
