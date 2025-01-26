package com.tookscan.tookscan.order.application.guest.usecase;

import com.tookscan.tookscan.core.annotation.bean.UseCase;
import com.tookscan.tookscan.order.application.guest.dto.request.CreateOrderRequestDto;
import com.tookscan.tookscan.order.application.guest.dto.response.CreateOrderResponseDto;

@UseCase
public interface CreateOrderUseCase {
    CreateOrderResponseDto execute(CreateOrderRequestDto requestDto);
}
