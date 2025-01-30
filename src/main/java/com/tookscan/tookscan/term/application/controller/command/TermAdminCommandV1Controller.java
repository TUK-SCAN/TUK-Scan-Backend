package com.tookscan.tookscan.term.application.controller.command;

import com.tookscan.tookscan.core.dto.ResponseDto;
import com.tookscan.tookscan.term.application.dto.request.CreateAdminTermRequestDto;
import com.tookscan.tookscan.term.application.usecase.CreateAdminTermUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admins")
public class TermAdminCommandV1Controller {

    private final CreateAdminTermUseCase createAdminTermUseCase;

    /**
     * 8.1.1 (관리자) 약관 추가
     */
     @PostMapping("/terms")
    public ResponseDto<Void> createAdminTerm(
             @RequestBody @Valid CreateAdminTermRequestDto requestDto
     ) {
         createAdminTermUseCase.execute(requestDto);
         return ResponseDto.created(null);
     }
}
