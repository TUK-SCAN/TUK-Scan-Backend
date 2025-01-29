package com.tookscan.tookscan.account.application.controller.command;

import com.tookscan.tookscan.account.application.dto.request.CreateAdminGroupRequestDto;
import com.tookscan.tookscan.account.application.usecase.CreateAdminGroupUseCase;
import com.tookscan.tookscan.core.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admins")
public class AccountAdminCommandV1Controller {

    private final CreateAdminGroupUseCase createAdminGroupUseCase;

    /**
     * 3.1.1 (관리자) 그룹 만들기
     */
    @PostMapping("/groups")
    public ResponseDto<Void> createAdminGroup(
            @RequestBody @Valid CreateAdminGroupRequestDto requestDto
    ) {
        createAdminGroupUseCase.execute(requestDto);
        return ResponseDto.created(null);
    }
}
