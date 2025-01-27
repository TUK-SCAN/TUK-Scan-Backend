package com.tookscan.tookscan.order.application.service;

import com.tookscan.tookscan.order.application.dto.response.ReadAdminOrderBriefsResponseDto;
import com.tookscan.tookscan.order.application.usecase.ReadAdminOrderBriefsUseCase;
import com.tookscan.tookscan.order.repository.mysql.custom.OrderRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReadAdminOrderBriefsService implements ReadAdminOrderBriefsUseCase {

    private final OrderRepositoryCustom orderRepositoryCustom;

    @Override
    @Transactional(readOnly = true)
    public ReadAdminOrderBriefsResponseDto execute() {
        return ReadAdminOrderBriefsResponseDto.of(orderRepositoryCustom.findOrderStatusCounts());
    }

}
