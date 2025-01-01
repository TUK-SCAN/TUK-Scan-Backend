package com.tukscan.tukscan.security.application.service;

import com.tukscan.tukscan.security.application.usecase.LogoutUseCase;
import com.tukscan.tukscan.security.info.CustomUserPrincipal;
import com.tukscan.tukscan.security.repository.redis.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutUseCase {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public void execute(CustomUserPrincipal principal) {
        UUID accountId = principal.getId();

        refreshTokenRepository.deleteById(accountId);
    }
}
