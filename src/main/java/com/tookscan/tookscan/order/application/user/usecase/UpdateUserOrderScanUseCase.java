package com.tookscan.tookscan.order.application.user.usecase;

import com.tookscan.tookscan.core.annotation.bean.UseCase;
import java.util.UUID;

@UseCase
public interface UpdateUserOrderScanUseCase {
    void execute(UUID accountId, Long orderId);
}
