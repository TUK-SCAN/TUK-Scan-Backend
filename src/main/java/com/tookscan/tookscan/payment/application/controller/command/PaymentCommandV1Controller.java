package com.tookscan.tookscan.payment.application.controller.command;

import com.tookscan.tookscan.core.dto.ResponseDto;
import com.tookscan.tookscan.payment.application.dto.request.ConfirmPaymentRequestDto;
import com.tookscan.tookscan.payment.application.dto.request.CreatePaymentRequestDto;
import com.tookscan.tookscan.payment.application.usecase.ConfirmPaymentUseCase;
import com.tookscan.tookscan.payment.application.usecase.CreatePaymentUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/payments")
public class PaymentCommandV1Controller {
    private final CreatePaymentUseCase createPaymentUseCase;
    private final ConfirmPaymentUseCase confirmPaymentUseCase;

    @PostMapping("")
    public ResponseDto<?> createPayment(
            @RequestBody @Valid CreatePaymentRequestDto requestDto
    ) {
        return ResponseDto.created(createPaymentUseCase.execute(requestDto));
    }

    @PatchMapping("")
    public ResponseDto<?> confirmPayment(
            @RequestBody @Valid ConfirmPaymentRequestDto requestDto
    ) {
        return ResponseDto.ok(confirmPaymentUseCase.execute(requestDto));
    }
}
