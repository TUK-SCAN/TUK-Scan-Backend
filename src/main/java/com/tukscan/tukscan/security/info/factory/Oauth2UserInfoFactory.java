package com.tukscan.tukscan.security.info.factory;

import com.tukscan.tukscan.security.domain.type.ESecurityProvider;
import com.tukscan.tukscan.security.info.KakaoOauth2UserInfo;
import com.tukscan.tukscan.security.info.GoogleOauth2UserInfo;

import java.util.Map;

public class Oauth2UserInfoFactory {
    public static Oauth2UserInfo getOauth2UserInfo(ESecurityProvider provider, Map<String, Object> attributes){
        switch (provider) {
            case KAKAO:
                return new KakaoOauth2UserInfo(attributes);
            case GOOGLE:
                return new GoogleOauth2UserInfo(attributes);
            default:
                throw new IllegalAccessError("잘못된 제공자 입니다.");
        }
    }
}