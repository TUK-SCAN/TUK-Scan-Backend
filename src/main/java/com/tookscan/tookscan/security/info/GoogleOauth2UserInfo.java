package com.tookscan.tookscan.security.info;

import com.tookscan.tookscan.security.info.factory.Oauth2UserInfo;

import java.util.Map;

public class GoogleOauth2UserInfo extends Oauth2UserInfo {
    public GoogleOauth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        if (!attributes.containsKey("sub")) {
            throw new IllegalStateException("Google OAuth2 User Info에 'sub' 키가 없습니다. attributes: " + attributes);
        }
        return (String) attributes.get("sub");
    }
}
