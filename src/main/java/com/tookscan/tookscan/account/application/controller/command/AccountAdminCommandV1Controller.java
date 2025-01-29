package com.tookscan.tookscan.account.application.controller.command;

import com.tookscan.tookscan.account.application.dto.request.CreateAdminGroupRequestDto;
import com.tookscan.tookscan.account.application.dto.request.CreateAdminUserGroupRequestDto;
import com.tookscan.tookscan.account.application.usecase.CreateAdminGroupUseCase;
import com.tookscan.tookscan.account.application.usecase.CreateAdminUserGroupUseCase;
import com.tookscan.tookscan.core.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admins")
public class AccountAdminCommandV1Controller {

    private final CreateAdminGroupUseCase createAdminGroupUseCase;
    private final CreateAdminUserGroupUseCase createAdminUserGroupUseCase;

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

    /**
     * 3.4.3 (관리자) 사용자 그룹 지정
     */
    @PutMapping("/groups/users")
    public ResponseDto<Void> createAdminUserGroup(
            @RequestBody @Valid CreateAdminUserGroupRequestDto requestDto
    ) {
        createAdminUserGroupUseCase.execute(requestDto);
        return ResponseDto.ok(null);
    }

}
