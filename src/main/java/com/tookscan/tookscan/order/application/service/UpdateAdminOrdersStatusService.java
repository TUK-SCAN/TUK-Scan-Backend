package com.tookscan.tookscan.order.application.service;

import com.tookscan.tookscan.order.application.dto.request.UpdateAdminOrdersStatusRequestDto;
import com.tookscan.tookscan.order.application.usecase.UpdateAdminOrdersStatusUseCase;
import com.tookscan.tookscan.order.repository.mysql.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateAdminOrdersStatusService implements UpdateAdminOrdersStatusUseCase {

    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public void execute(UpdateAdminOrdersStatusRequestDto requestDto) {
        requestDto.orderIds().forEach(orderId -> {
            orderRepository.findById(orderId)
                    .ifPresent(order -> order.updateStatus(requestDto.status()));
        });
    }

}
