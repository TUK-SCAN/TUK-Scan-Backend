package com.tookscan.tookscan.mail.application.controller.command;

import com.tookscan.tookscan.core.dto.ResponseDto;
import com.tookscan.tookscan.mail.application.dto.request.SendTestMailRequestDto;
import com.tookscan.tookscan.mail.application.usecase.SendTestMailUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class MailCommandV1Controller {
    private final SendTestMailUseCase sendTestMailUseCase;


    /**
     * 0.1.1 테스트 메일 발송
     * @param requestDto
     * @return
     */
    @PostMapping("/test-email")
    public ResponseDto<Void> sendTestMail(
            @RequestBody @Valid SendTestMailRequestDto requestDto
    ) {
        sendTestMailUseCase.execute(requestDto);
        return ResponseDto.ok(null);
    }

}
