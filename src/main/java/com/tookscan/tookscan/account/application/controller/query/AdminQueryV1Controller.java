package com.tookscan.tookscan.account.application.controller.query;

import com.tookscan.tookscan.account.application.dto.response.ReadAdminUserOverviewResponseDto;
import com.tookscan.tookscan.account.application.usecase.ReadAdminUserOverviewUseCase;
import com.tookscan.tookscan.core.annotation.security.AccountID;
import com.tookscan.tookscan.core.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admins")
public class AdminQueryV1Controller {

    private final ReadAdminUserOverviewUseCase readAdminUserOverviewUseCase;

    /**
     * 3.6 (관리자) 유저 리스트 조회
     */
    @GetMapping("/users/overviews")
    public ResponseDto<ReadAdminUserOverviewResponseDto> readAdminUserOverview(
            @AccountID UUID accountId,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long groupId,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ResponseDto.ok(readAdminUserOverviewUseCase.execute(
                accountId,
                search,
                groupId,
                startDate,
                endDate,
                page,
                size
        ));
    }
}
