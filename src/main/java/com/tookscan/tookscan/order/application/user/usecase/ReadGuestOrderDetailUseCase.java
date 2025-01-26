package com.tookscan.tookscan.order.application.user.usecase;

import com.tookscan.tookscan.core.annotation.bean.UseCase;
import com.tookscan.tookscan.order.application.user.dto.response.ReadGuestOrderDetailResponseDto;

@UseCase
public interface ReadGuestOrderDetailUseCase {
    ReadGuestOrderDetailResponseDto execute(String name, Long orderNumber);
}
