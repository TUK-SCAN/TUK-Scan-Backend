package com.tookscan.tookscan.account.application.service;

import com.tookscan.tookscan.account.application.dto.response.ReadUserSummaryResponseDto;
import com.tookscan.tookscan.account.application.usecase.ReadUserSummaryUseCase;
import com.tookscan.tookscan.account.domain.User;
import com.tookscan.tookscan.account.repository.mysql.UserRepository;
import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReadUserSummaryService implements ReadUserSummaryUseCase {

    private final UserRepository userRepository;

    @Override
    public ReadUserSummaryResponseDto execute(UUID accountId) {

        // User 조회
        User user = userRepository.findById(accountId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // UserSummaryResponseDto로 변환하여 반환
        return ReadUserSummaryResponseDto.fromEntity(user);
    }
}
