package com.tukscan.tukscan.security.application.service;

import com.tukscan.tukscan.core.exception.error.ErrorCode;
import com.tukscan.tukscan.core.exception.type.CommonException;
import com.tukscan.tukscan.security.application.dto.request.ChangePasswordRequestDto;
import com.tukscan.tukscan.security.application.usecase.ChangePasswordUseCase;
import com.tukscan.tukscan.security.domain.mysql.Account;
import com.tukscan.tukscan.security.domain.service.AccountService;
import com.tukscan.tukscan.security.repository.mysql.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChangePasswordService implements ChangePasswordUseCase {

    private final AccountRepository accountRepository;

    private final AccountService accountService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void execute(UUID accountId, ChangePasswordRequestDto requestDto) {

        // 계정 조회
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // 이전 비밀번호 일치 여부 확인
        accountService.checkPassword(account, requestDto.oldPassword());

        // 비밀번호 변경
        accountService.changePassword(account, bCryptPasswordEncoder.encode(requestDto.newPassword()));

        // 변경된 비밀번호 저장
        accountRepository.save(account);

    }
}
