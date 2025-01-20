package com.tookscan.tookscan.account.application.controller.command;

import com.tookscan.tookscan.account.application.dto.request.UpdateUserRequestDto;
import com.tookscan.tookscan.account.application.usecase.UpdateUserUseCase;
import com.tookscan.tookscan.core.annotation.security.AccountID;
import com.tookscan.tookscan.core.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserCommandV1Controller {

    private final UpdateUserUseCase updateUserUseCase;

    /**
     * 3.12 유저 정보 수정
     */
    @PutMapping("/v1/users")
    @Operation(summary = "유저 정보 수정", description = "유저의 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "401", description = "휴대폰 인증 완료 안된상태로 휴대폰 번호 수정 시도"),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")
    })
    public ResponseDto<Void> updateUser(
            @AccountID UUID accountId,
            @Valid @RequestBody UpdateUserRequestDto requestDto
    ) {
        updateUserUseCase.execute(accountId, requestDto);
        return ResponseDto.ok(null);
    }

}
