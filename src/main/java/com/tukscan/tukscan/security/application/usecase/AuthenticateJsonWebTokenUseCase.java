package com.tukscan.tukscan.security.application.usecase;

import com.tukscan.tukscan.core.annotation.bean.UseCase;
import com.tukscan.tukscan.security.info.CustomUserPrincipal;

import java.util.UUID;

@UseCase
public interface AuthenticateJsonWebTokenUseCase {

    CustomUserPrincipal execute(UUID accountId);
}
