package com.tukscan.tukscan.security.application.service;

import com.tukscan.tukscan.security.application.dto.response.DefaultJsonWebTokenDto;
import com.tukscan.tukscan.security.application.usecase.LoginByDefaultUseCase;
import com.tukscan.tukscan.security.domain.service.RefreshTokenService;
import com.tukscan.tukscan.security.info.CustomUserPrincipal;
import com.tukscan.tukscan.security.repository.redis.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoginDefaultService implements LoginByDefaultUseCase {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenService refreshTokenService;

    @Override
    @Transactional
    public void execute(CustomUserPrincipal principal, DefaultJsonWebTokenDto jsonWebTokenDto) {
        UUID accountId = principal.getId();
        String refreshToken = jsonWebTokenDto.getRefreshToken();

        if (refreshToken != null) {
            refreshTokenRepository.save(refreshTokenService.createRefreshToken(accountId, refreshToken));
        }
    }
}
