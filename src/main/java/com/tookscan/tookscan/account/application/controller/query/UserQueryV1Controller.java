package com.tookscan.tookscan.account.application.controller.query;

import com.tookscan.tookscan.security.application.dto.response.ReadUserBriefResponseDto;
import com.tookscan.tookscan.account.application.dto.response.ReadUserUserDetailResponseDto;
import com.tookscan.tookscan.account.application.dto.response.ReadUserUserSummaryResponseDto;
import com.tookscan.tookscan.security.application.usecase.ReadUserBriefUseCase;
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
public class UserQueryV1Controller {

    private final ReadUserBriefUseCase readUserBriefUseCase;
    private final ReadUserUserDetailUseCase readUserUserDetailUseCase;
    private final ReadUserUserSummaryUseCase readUserUserSummaryUseCase;

    /**
     * 3.8 유저 간단 정보 조회
     */
    @GetMapping("/v1/users/briefs")
    @Operation(summary = "유저 간단 정보 조회", description = "계정 유형(ADMIN, USER)과 이름을 포함한 유저의 기본 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")
    })
    public ResponseDto<ReadUserBriefResponseDto> readAccountBrief(@AccountID UUID accountId) {
        return ResponseDto.ok(readUserBriefUseCase.execute(accountId));
    }

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
