package com.tukscan.tukscan.security.domain.redis;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "authentication_code", timeToLive = 60 * 5) // 5분
public class AuthenticationCode {

    @Id
    private String phoneNumber;

    private String value;

    private Boolean isVerified = false;

    @Builder
    public AuthenticationCode(String phoneNumber, String value) {
        this.phoneNumber = phoneNumber;
        this.value = value;
    }

    public void verify() {
        this.isVerified = true;
    }
}
