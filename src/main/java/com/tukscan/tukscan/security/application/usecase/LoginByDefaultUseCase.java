package com.tukscan.tukscan.security.application.usecase;

import com.tukscan.tukscan.core.annotation.bean.UseCase;
import com.tukscan.tukscan.security.application.dto.response.DefaultJsonWebTokenDto;
import com.tukscan.tukscan.security.info.CustomUserPrincipal;

@UseCase
public interface LoginByDefaultUseCase {

    /**
     * Security에서 사용되는 Login 유스케이스
     * @param principal UserPrincipal
     * @param jsonWebTokenDto DefaultJsonWebTokenDto
     */
    void execute(CustomUserPrincipal principal, DefaultJsonWebTokenDto jsonWebTokenDto);
}
