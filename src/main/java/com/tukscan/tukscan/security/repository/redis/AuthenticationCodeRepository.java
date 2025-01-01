package com.tukscan.tukscan.security.repository.redis;

import com.tukscan.tukscan.security.domain.redis.AuthenticationCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationCodeRepository extends CrudRepository<AuthenticationCode, String>{
}
