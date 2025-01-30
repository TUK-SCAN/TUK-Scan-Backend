package com.tookscan.tookscan.order.application.service;

import com.tookscan.tookscan.order.application.usecase.DeleteAdminOrdersUseCase;
import com.tookscan.tookscan.order.domain.Order;
import com.tookscan.tookscan.order.repository.OrderRepository;
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
        List<Order> orders = orderRepository.findAllByIdOrElseThrow(orderIds);
        orderRepository.deleteAll(orders);
    }
}
