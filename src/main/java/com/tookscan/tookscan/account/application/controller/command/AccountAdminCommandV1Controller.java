package com.tookscan.tookscan.account.application.controller.command;

import com.tookscan.tookscan.account.application.dto.request.CreateAdminGroupRequestDto;
import com.tookscan.tookscan.account.application.dto.request.CreateAdminUserGroupRequestDto;
import com.tookscan.tookscan.account.application.dto.request.UpdateAdminUserRequestDto;
import com.tookscan.tookscan.account.application.dto.request.UpdateAdminGroupRequestDto;
import com.tookscan.tookscan.account.application.usecase.CreateAdminGroupUseCase;
import com.tookscan.tookscan.account.application.usecase.CreateAdminUserGroupUseCase;
import com.tookscan.tookscan.account.application.usecase.DeleteAdminGroupUseCase;
import com.tookscan.tookscan.account.application.usecase.UpdateAdminUserUseCase;
import com.tookscan.tookscan.account.application.usecase.UpdateAdminGroupUseCase;
import com.tookscan.tookscan.core.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admins")
public class AccountAdminCommandV1Controller {

    private final CreateAdminGroupUseCase createAdminGroupUseCase;
    private final UpdateAdminUserUseCase updateAdminUserUseCase;
    private final UpdateAdminGroupUseCase updateAdminGroupUseCase;
    private final CreateAdminUserGroupUseCase createAdminUserGroupUseCase;
    private final DeleteAdminGroupUseCase deleteAdminGroupUseCase;

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
     * 3.3.1 (관리자) 그룹 수정
     */
    @PatchMapping("/groups/{id}")
    public ResponseDto<Void> updateAdminGroup(
            @PathVariable Long id,
            @RequestBody @Valid UpdateAdminGroupRequestDto requestDto
    ) {
        updateAdminGroupUseCase.execute(requestDto, id);
        return ResponseDto.ok(null);
    }

    /**
     * 3.4.2 (관리자) 유저 정보 수정
     */
    @PutMapping("/users/{id}")
    public ResponseDto<Void> updateAdminUser(
            @RequestBody @Valid UpdateAdminUserRequestDto requestDto,
            @PathVariable UUID id
    ) {
        updateAdminUserUseCase.execute(requestDto, id);
        return ResponseDto.ok(null);
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

    /**
     * 3.5.1 (관리자) 그룹 삭제
     */
    @DeleteMapping("/groups/{id}")
    public ResponseDto<Void> deleteAdminGroup(
            @PathVariable Long id
    ) {
        deleteAdminGroupUseCase.execute(id);
        return ResponseDto.ok(null);
    }

}
