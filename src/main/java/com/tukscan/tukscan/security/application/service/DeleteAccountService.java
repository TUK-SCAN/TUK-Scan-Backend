package com.tukscan.tukscan.security.application.service;

import com.tukscan.tukscan.security.application.usecase.DeleteAccountUseCase;
import com.tukscan.tukscan.security.repository.mysql.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteAccountService implements DeleteAccountUseCase {

    private final AccountRepository accountRepository;

    @Override
    public void execute(UUID accountId) {
        accountRepository.deleteById(accountId);
    }
}
