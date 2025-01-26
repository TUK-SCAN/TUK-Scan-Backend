package com.tookscan.tookscan.account.application.controller.query;

import com.tookscan.tookscan.account.application.dto.response.ReadUserUserDetailResponseDto;
import com.tookscan.tookscan.account.application.dto.response.ReadUserUserSummaryResponseDto;
import com.tookscan.tookscan.account.application.usecase.ReadUserUserDetailUseCase;
import com.tookscan.tookscan.account.application.usecase.ReadUserUserSummaryUseCase;
import com.tookscan.tookscan.core.annotation.security.AccountID;
import com.tookscan.tookscan.core.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AccountUserQueryV1Controller {

    private final ReadUserUserDetailUseCase readUserUserDetailUseCase;
    private final ReadUserUserSummaryUseCase readUserUserSummaryUseCase;

    /**
     * 3.9 사용자 상세 정보 조회
     */
    @GetMapping("/v1/users/details")
    @Operation(summary = "사용자 상세 정보 조회", description = "사용자의 상세 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")
    })
    public ResponseDto<ReadUserUserDetailResponseDto> readUserDetail(@AccountID UUID accountId) {
        return ResponseDto.ok(readUserUserDetailUseCase.execute(accountId));
    }

    /**
     * 3.10 유저 배송 정보 조회
     */
    @GetMapping("/v1/users/summaries")
    @Operation(summary = "유저 배송 정보 조회", description = "배송 요청 시 필요한 사용자 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")
    })
    public ResponseDto<ReadUserUserSummaryResponseDto> readUserSummary(@AccountID UUID accountId) {
        return ResponseDto.ok(readUserUserSummaryUseCase.execute(accountId));
    }

}
