package com.tookscan.tookscan.order.application.service;

import com.tookscan.tookscan.order.application.dto.response.ReadAdminOrderBriefResponseDto;
import com.tookscan.tookscan.order.application.usecase.ReadAdminOrderBriefUseCase;
import com.tookscan.tookscan.order.repository.mysql.custom.OrderRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadAdminOrderBriefService implements ReadAdminOrderBriefUseCase {

    private final OrderRepositoryCustom orderRepositoryCustom;

    @Override
    public ReadAdminOrderBriefResponseDto execute() {
        return ReadAdminOrderBriefResponseDto.of(orderRepositoryCustom.findOrderStatusCounts());
    }

}
