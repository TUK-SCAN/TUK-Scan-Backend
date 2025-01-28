package com.tookscan.tookscan.order.application.service;

import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import com.tookscan.tookscan.order.application.usecase.DeleteAdminOrdersUseCase;
import com.tookscan.tookscan.order.domain.Order;
import com.tookscan.tookscan.order.repository.mysql.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteAdminOrdersService implements DeleteAdminOrdersUseCase {

    private final OrderRepository orderRepository;
    @Override
    @Transactional
    public void execute(List<Long> orderIds) {
        orderIds.forEach(orderId -> {
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ORDER));
            orderRepository.delete(order);
        });
    }
}
