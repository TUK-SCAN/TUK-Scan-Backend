package com.tookscan.tookscan.account.application.service;

import com.tookscan.tookscan.account.application.dto.response.ReadAccountBriefResponseDto;
import com.tookscan.tookscan.account.application.usecase.ReadAccountBriefUseCase;
import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import com.tookscan.tookscan.security.domain.mysql.Account;
import com.tookscan.tookscan.security.repository.mysql.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReadAccountBriefService implements ReadAccountBriefUseCase {

    private final AccountRepository accountRepository;

    @Override
    public ReadAccountBriefResponseDto execute(UUID accountId) {

        // Account 조회
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ACCOUNT));

        // Account 정보를 ReadUserBriefResponseDto로 변환
        return ReadAccountBriefResponseDto.fromEntity(account);
    }
}
