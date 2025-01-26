package com.tookscan.tookscan.order.application.user.usecase;

import com.tookscan.tookscan.core.annotation.bean.UseCase;
import com.tookscan.tookscan.order.application.user.dto.request.CreateUserOrderRequestDto;
import com.tookscan.tookscan.order.application.user.dto.response.CreateUserOrderResponseDto;
import java.util.UUID;

@UseCase
public interface CreateUserOrderUseCase {
    CreateUserOrderResponseDto execute(UUID accountId, CreateUserOrderRequestDto requestDto);
}
