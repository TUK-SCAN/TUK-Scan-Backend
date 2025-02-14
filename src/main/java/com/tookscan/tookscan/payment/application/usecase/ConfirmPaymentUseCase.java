package com.tookscan.tookscan.payment.application.usecase;

import com.tookscan.tookscan.core.annotation.bean.UseCase;
import com.tookscan.tookscan.payment.application.dto.request.ConfirmPaymentRequestDto;
import com.tookscan.tookscan.payment.application.dto.response.ConfirmPaymentResponseDto;

@UseCase
public interface ConfirmPaymentUseCase {
    ConfirmPaymentResponseDto execute(ConfirmPaymentRequestDto requestDto);
}
