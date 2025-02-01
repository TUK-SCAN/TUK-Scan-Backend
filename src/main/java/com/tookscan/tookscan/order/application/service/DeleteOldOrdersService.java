package com.tookscan.tookscan.order.application.service;

import com.tookscan.tookscan.order.application.usecase.DeleteOldOrdersUseCase;
import com.tookscan.tookscan.order.repository.OrderRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteOldOrdersService implements DeleteOldOrdersUseCase {

    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public void execute() {
        LocalDateTime twoWeeksAgo = LocalDateTime.now().minusWeeks(2);
        List<Long> orderIds = orderRepository.findIdsByCreatedAtBefore(twoWeeksAgo);
        orderRepository.deleteAllById(orderIds);
    }
}
