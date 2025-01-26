package com.tookscan.tookscan.order.application.user.usecase;

import com.tookscan.tookscan.core.annotation.bean.UseCase;
import com.tookscan.tookscan.order.application.user.dto.response.ReadUserOrderDetailResponseDto;
import java.util.UUID;

@UseCase
public interface ReadUserOrderDetailUseCase {
    ReadUserOrderDetailResponseDto execute(UUID accountID, Long orderId);
}
