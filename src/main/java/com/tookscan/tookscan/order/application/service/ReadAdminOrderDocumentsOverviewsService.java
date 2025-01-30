package com.tookscan.tookscan.order.application.service;

import com.tookscan.tookscan.order.application.dto.response.ReadAdminOrderDocumentsOverviewsResponseDto;
import com.tookscan.tookscan.order.application.usecase.ReadAdminOrderDocumentsOverviewsUseCase;
import com.tookscan.tookscan.order.domain.Order;
import com.tookscan.tookscan.order.repository.OrderRepository;
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
        Order order = orderRepository.findByIdOrElseThrow(orderId);

        return ReadAdminOrderDocumentsOverviewsResponseDto.fromEntity(order);
    }
}
