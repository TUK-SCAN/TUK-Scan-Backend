package com.tookscan.tookscan.order.application.usecase;

import com.tookscan.tookscan.core.annotation.bean.UseCase;
import com.tookscan.tookscan.order.application.dto.request.GuestCreateOrderRequestDto;
import com.tookscan.tookscan.order.application.dto.response.GuestCreateOrderResponseDto;

@UseCase
public interface GuestCreateOrderUseCase {
    GuestCreateOrderResponseDto execute(GuestCreateOrderRequestDto requestDto);
}
