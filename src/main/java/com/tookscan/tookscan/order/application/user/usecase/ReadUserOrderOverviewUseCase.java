package com.tookscan.tookscan.order.application.user.usecase;

import com.tookscan.tookscan.core.annotation.bean.UseCase;
import com.tookscan.tookscan.order.application.user.dto.response.ReadUserOrderOverviewResponseDto;
import java.util.UUID;

@UseCase
public interface ReadUserOrderOverviewUseCase {
    ReadUserOrderOverviewResponseDto execute(UUID accountId, Integer page, Integer size, String sort, String search, String direction);
}
