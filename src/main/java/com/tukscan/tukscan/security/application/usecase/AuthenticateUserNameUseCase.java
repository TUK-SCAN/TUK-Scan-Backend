package com.tukscan.tukscan.security.application.usecase;

import com.tukscan.tukscan.core.annotation.bean.UseCase;
import org.springframework.security.core.userdetails.UserDetailsService;

@UseCase
public interface AuthenticateUserNameUseCase extends UserDetailsService {
}
