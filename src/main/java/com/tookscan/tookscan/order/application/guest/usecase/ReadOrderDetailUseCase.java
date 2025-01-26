package com.tookscan.tookscan.order.application.guest.usecase;

import com.tookscan.tookscan.core.annotation.bean.UseCase;
import com.tookscan.tookscan.order.application.guest.dto.response.ReadOrderDetailResponseDto;

@UseCase
public interface ReadOrderDetailUseCase {
    ReadOrderDetailResponseDto execute(String name, Long orderNumber);
}
