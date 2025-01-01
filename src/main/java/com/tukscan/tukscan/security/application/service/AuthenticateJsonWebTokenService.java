package com.tukscan.tukscan.security.application.service;

import com.tukscan.tukscan.core.exception.error.ErrorCode;
import com.tukscan.tukscan.core.exception.type.CommonException;
import com.tukscan.tukscan.security.application.usecase.AuthenticateJsonWebTokenUseCase;
import com.tukscan.tukscan.security.domain.mysql.Account;
import com.tukscan.tukscan.security.domain.service.AccountService;
import com.tukscan.tukscan.security.info.CustomUserPrincipal;
import com.tukscan.tukscan.security.repository.mysql.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticateJsonWebTokenService implements AuthenticateJsonWebTokenUseCase {

    private final AccountRepository accountRepository;
    private final AccountService accountService;

    @Override
    public CustomUserPrincipal execute(UUID accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ACCOUNT));

        return accountService.createCustomUserPrincipalByAccount(account);
    }
}
