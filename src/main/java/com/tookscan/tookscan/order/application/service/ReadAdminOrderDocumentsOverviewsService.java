package com.tookscan.tookscan.order.application.service;

import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import com.tookscan.tookscan.order.application.dto.response.ReadAdminOrderDocumentsOverviewsResponseDto;
import com.tookscan.tookscan.order.application.usecase.ReadAdminOrderDocumentsOverviewsUseCase;
import com.tookscan.tookscan.order.domain.Order;
import com.tookscan.tookscan.order.repository.mysql.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReadAdminOrderDocumentsOverviewsService implements ReadAdminOrderDocumentsOverviewsUseCase {

    private final OrderRepository orderRepository;

    @Override
    @Transactional(readOnly = true)
    public ReadAdminOrderDocumentsOverviewsResponseDto execute(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ORDER, "주문 ID: " + orderId));

        return ReadAdminOrderDocumentsOverviewsResponseDto.fromEntity(order);
    }
}
