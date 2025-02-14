package com.tookscan.tookscan.payment.application.usecase;

import com.tookscan.tookscan.core.annotation.bean.UseCase;
import com.tookscan.tookscan.payment.application.dto.request.CreatePaymentRequestDto;
import com.tookscan.tookscan.payment.application.dto.response.CreatePaymentResponseDto;

@UseCase
public interface CreatePaymentUseCase {

    CreatePaymentResponseDto execute(CreatePaymentRequestDto requestDto);
}
