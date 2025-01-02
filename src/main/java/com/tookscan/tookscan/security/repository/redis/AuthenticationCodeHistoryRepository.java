package com.tookscan.tookscan.security.repository.redis;

import com.tookscan.tookscan.security.domain.redis.AuthenticationCodeHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationCodeHistoryRepository extends CrudRepository<AuthenticationCodeHistory, String> {
}
