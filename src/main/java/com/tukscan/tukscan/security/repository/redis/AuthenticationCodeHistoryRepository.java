package com.tukscan.tukscan.security.repository.redis;

import com.tukscan.tukscan.security.domain.redis.AuthenticationCodeHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationCodeHistoryRepository extends CrudRepository<AuthenticationCodeHistory, String> {
}
