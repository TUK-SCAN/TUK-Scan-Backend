package com.tookscan.tookscan.account.application.controller.query;

import com.tookscan.tookscan.account.application.dto.response.ReadAccountBriefResponseDto;
import com.tookscan.tookscan.account.application.usecase.ReadAccountBriefUseCase;
import com.tookscan.tookscan.core.annotation.security.AccountID;
import com.tookscan.tookscan.core.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AccountQueryV1Controller {

    private final ReadAccountBriefUseCase readAccountBriefUseCase;

    @GetMapping("/v1/accounts/briefs")
    public ResponseDto<ReadAccountBriefResponseDto> readAccountBrief(@AccountID UUID accountId) {
        return ResponseDto.ok(readAccountBriefUseCase.execute(accountId));
    }
}
