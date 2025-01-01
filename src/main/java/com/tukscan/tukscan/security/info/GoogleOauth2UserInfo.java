package com.tukscan.tukscan.security.info;

import com.tukscan.tukscan.security.info.factory.Oauth2UserInfo;

import java.util.Map;

public class GoogleOauth2UserInfo extends Oauth2UserInfo {
    public GoogleOauth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }
}