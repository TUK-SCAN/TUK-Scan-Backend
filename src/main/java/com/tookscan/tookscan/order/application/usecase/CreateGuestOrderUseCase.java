package com.tookscan.tookscan.order.application.usecase;

import com.tookscan.tookscan.core.annotation.bean.UseCase;
import com.tookscan.tookscan.order.application.dto.request.CreateGuestOrderRequestDto;
import com.tookscan.tookscan.order.application.dto.response.CreateGuestOrderResponseDto;

@UseCase
public interface CreateGuestOrderUseCase {
    CreateGuestOrderResponseDto execute(CreateGuestOrderRequestDto requestDto);
}
