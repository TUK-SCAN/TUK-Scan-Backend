package com.tookscan.tookscan.account.application.service;

import com.tookscan.tookscan.account.application.dto.response.ReadUserDetailResponseDto;
import com.tookscan.tookscan.account.application.usecase.ReadUserDetailUseCase;
import com.tookscan.tookscan.account.domain.User;
import com.tookscan.tookscan.account.repository.mysql.UserRepository;
import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReadUserDetailService implements ReadUserDetailUseCase {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public ReadUserDetailResponseDto execute(UUID accountId) {

        // User 조회
        User user = userRepository.findById(accountId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // User 정보를 ReadUserDetailResponseDto로 변환
        return ReadUserDetailResponseDto.fromEntity(user);
    }
}
