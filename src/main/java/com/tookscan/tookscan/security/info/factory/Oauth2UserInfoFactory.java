package com.tookscan.tookscan.security.info.factory;

import com.tookscan.tookscan.security.domain.type.ESecurityProvider;
import com.tookscan.tookscan.security.info.KakaoOauth2UserInfo;
import com.tookscan.tookscan.security.info.GoogleOauth2UserInfo;

import java.util.Map;

public class Oauth2UserInfoFactory {
    public static Oauth2UserInfo getOauth2UserInfo(ESecurityProvider provider, Map<String, Object> attributes){
        if (attributes == null || attributes.isEmpty()) {
            throw new IllegalStateException("OAuth2 Attributes가 비어 있습니다. provider: " + provider);
        }
        return switch (provider) {
            case KAKAO -> new KakaoOauth2UserInfo(attributes);
            case GOOGLE -> new GoogleOauth2UserInfo(attributes);
            default -> throw new IllegalAccessError("잘못된 제공자 입니다.");
        };
    }
}
