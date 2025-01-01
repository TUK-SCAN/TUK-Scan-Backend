package com.tukscan.tukscan.security.application.service;

import com.tukscan.tukscan.security.application.usecase.AuthenticateUserNameUseCase;
import com.tukscan.tukscan.security.domain.mysql.Account;
import com.tukscan.tukscan.security.domain.service.AccountService;
import com.tukscan.tukscan.security.domain.type.ESecurityProvider;
import com.tukscan.tukscan.security.repository.mysql.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateUserNameService implements AuthenticateUserNameUseCase {

    private final AccountRepository accountRepository;
    private final AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String serialId) throws UsernameNotFoundException {
        Account account = accountRepository.findBySerialIdAndProvider(serialId, ESecurityProvider.DEFAULT)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with serialId: " + serialId));

        return accountService.createCustomUserPrincipalByAccount(account);
    }
}
