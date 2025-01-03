package com.tookscan.tookscan.security.application.usecase;

import com.tookscan.tookscan.core.annotation.bean.UseCase;
import org.springframework.security.core.userdetails.UserDetailsService;

@UseCase
public interface AuthenticateUserNameUseCase extends UserDetailsService {
}
