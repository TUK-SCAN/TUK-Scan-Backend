package com.tukscan.tukscan.security.repository.mysql;

import com.tukscan.tukscan.security.domain.mysql.Account;
import com.tukscan.tukscan.security.domain.type.ESecurityProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findBySerialIdAndProvider(String serialId, ESecurityProvider provider);

    Optional<Account> findBySerialId(String serialId);

    Optional<Account> findByPhoneNumber(String phoneNumber);

    Optional<Account> findByPhoneNumberAndSerialId(String phoneNumber, String serialId);
}
