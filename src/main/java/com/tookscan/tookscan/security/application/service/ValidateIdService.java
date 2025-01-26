package com.tookscan.tookscan.security.application.service;

import com.tookscan.tookscan.security.application.dto.response.ValidationResponseDto;
import com.tookscan.tookscan.security.application.usecase.ValidateIdUseCase;
import com.tookscan.tookscan.security.repository.mysql.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidateIdService implements ValidateIdUseCase {

    private final AccountRepository accountRepository;

    @Override
    public ValidationResponseDto execute(String serialId) {
        return ValidationResponseDto.of(isValidateId(serialId));
    }

    /**
     * 중복된 아이디인지 확인
     * @param serialId 아이디
     * @return 중복된 아이디인지 여부
     */
    private Boolean isValidateId(String serialId) {
        return accountRepository.findBySerialId(serialId).isEmpty();
    }
}
