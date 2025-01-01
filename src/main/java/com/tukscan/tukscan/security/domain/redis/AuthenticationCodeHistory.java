package com.tukscan.tukscan.security.domain.redis;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "authentication_code_history", timeToLive = 60 * 30) // 30분
public class AuthenticationCodeHistory {
    @Id
    private String phoneNumber;

    private Integer count;

    private LocalDateTime lastSentAt;

    @Builder
    public AuthenticationCodeHistory(
            String phoneNumber,
            Integer count
    ) {
        this.phoneNumber = phoneNumber;
        this.count = count;

        this.lastSentAt = LocalDateTime.now();
    }

    public void incrementCount() {
        this.count++;
    }

    public void updateLastSentAt() {
        this.lastSentAt = LocalDateTime.now();
    }
}
