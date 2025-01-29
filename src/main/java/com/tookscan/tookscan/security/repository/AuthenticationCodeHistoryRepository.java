package com.tookscan.tookscan.security.repository;

import com.tookscan.tookscan.security.domain.redis.AuthenticationCodeHistory;

public interface AuthenticationCodeHistoryRepository {

    AuthenticationCodeHistory save(AuthenticationCodeHistory authenticationCodeHistory);

    void deleteById(String value);

    AuthenticationCodeHistory findByIdOrElseNull(String value);
}
