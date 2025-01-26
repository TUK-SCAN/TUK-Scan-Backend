package com.tookscan.tookscan.order.application.user.usecase;

import com.tookscan.tookscan.core.annotation.bean.UseCase;
import com.tookscan.tookscan.order.application.user.dto.request.CreateOrderRequestDto;
import com.tookscan.tookscan.order.application.user.dto.response.CreateOrderResponseDto;
import java.util.UUID;

@UseCase
public interface CreateOrderUseCase {
    CreateOrderResponseDto execute(UUID accountId, CreateOrderRequestDto requestDto);
}
