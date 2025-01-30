package com.tookscan.tookscan.security.repository;

import com.tookscan.tookscan.security.domain.redis.AuthenticationCode;

public interface AuthenticationCodeRepository {

    void save(AuthenticationCode authenticationCode);

    void deleteById(String value);

    AuthenticationCode findByIdOrElseNull(String value);

    AuthenticationCode findByIdOrElseThrow(String value);
}
