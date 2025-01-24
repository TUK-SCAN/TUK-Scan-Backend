package com.tookscan.tookscan.account.application.service;

import com.tookscan.tookscan.account.application.dto.response.ReadUserBriefResponseDto;
import com.tookscan.tookscan.account.application.usecase.ReadUserBriefUseCase;
import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import com.tookscan.tookscan.security.domain.mysql.Account;
import com.tookscan.tookscan.security.repository.mysql.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReadUserBriefService implements ReadUserBriefUseCase {

    private final AccountRepository accountRepository;

    @Override
    @Transactional(readOnly = true)
    public ReadUserBriefResponseDto execute(UUID accountId) {

        // Account 조회
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ACCOUNT));

        // Account 정보를 ReadUserBriefResponseDto로 변환
        return ReadUserBriefResponseDto.fromEntity(account);
    }
}
