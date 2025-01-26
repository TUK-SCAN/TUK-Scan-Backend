package com.tookscan.tookscan.order.application.user.usecase;

import com.tookscan.tookscan.core.annotation.bean.UseCase;
import com.tookscan.tookscan.order.application.user.dto.request.CreateGuestOrderRequestDto;
import com.tookscan.tookscan.order.application.user.dto.response.CreateGuestOrderResponseDto;

@UseCase
public interface CreateGuestOrderUseCase {
    CreateGuestOrderResponseDto execute(CreateGuestOrderRequestDto requestDto);
}
