package com.tookscan.tookscan.security.repository.redis;

import com.tookscan.tookscan.security.domain.redis.AuthenticationCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationCodeRedisRepository extends CrudRepository<AuthenticationCode, String>{
}
