package com.tookscan.tookscan.order.application.service;

import com.tookscan.tookscan.order.application.dto.response.ReadAdminOrderBriefResponseDto;
import com.tookscan.tookscan.order.application.usecase.ReadAdminOrderBriefUseCase;
import com.tookscan.tookscan.order.repository.mysql.custom.OrderRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReadAdminOrderBriefService implements ReadAdminOrderBriefUseCase {

    private final OrderRepositoryCustom orderRepositoryCustom;

    @Override
    @Transactional(readOnly = true)
    public ReadAdminOrderBriefResponseDto execute() {
        return ReadAdminOrderBriefResponseDto.of(orderRepositoryCustom.findOrderStatusCounts());
    }

}
