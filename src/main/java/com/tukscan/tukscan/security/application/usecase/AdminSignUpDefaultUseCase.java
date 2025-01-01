package com.tukscan.tukscan.security.application.usecase;

import com.tukscan.tukscan.core.annotation.bean.UseCase;
import com.tukscan.tukscan.security.application.dto.request.AdminSignUpDefaultRequestDto;

@UseCase
public interface AdminSignUpDefaultUseCase {

    void execute(AdminSignUpDefaultRequestDto requestDto);
}
