package com.tukscan.tukscan.security.application.usecase;

import com.tukscan.tukscan.core.annotation.bean.UseCase;
import com.tukscan.tukscan.security.application.dto.response.ValidationResponseDto;

@UseCase
public interface ValidateIdUseCase {

        /**
        * ID 유효성 검사
        * @param id 회원가입시 입력하는 ID
        * @return ValidationResponseDto
        */
        ValidationResponseDto execute(String id);
}
