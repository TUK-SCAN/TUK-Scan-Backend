package com.tookscan.tookscan.security.repository;

import com.tookscan.tookscan.security.domain.redis.RefreshToken;

import java.util.UUID;

public interface RefreshTokenRepository {

    void save(RefreshToken refreshToken);

    void deleteById(UUID value);

    RefreshToken findByValueOrElseThrow(String value);
}
