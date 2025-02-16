package com.tookscan.tookscan.payment.application.controller.command;

import com.tookscan.tookscan.core.dto.ResponseDto;
import com.tookscan.tookscan.payment.application.dto.request.ConfirmPaymentRequestDto;
import com.tookscan.tookscan.payment.application.usecase.ConfirmPaymentUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/payments")
public class PaymentCommandV1Controller {
    private final ConfirmPaymentUseCase confirmPaymentUseCase;

    @PostMapping("")
    public ResponseDto<?> confirmPayment(
            @RequestBody @Valid ConfirmPaymentRequestDto requestDto
    ) {
        confirmPaymentUseCase.execute(requestDto);
        return ResponseDto.created(null);
    }
}
