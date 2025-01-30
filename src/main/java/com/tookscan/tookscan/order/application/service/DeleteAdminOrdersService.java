package com.tookscan.tookscan.order.application.service;

import com.tookscan.tookscan.order.application.usecase.DeleteAdminOrdersUseCase;
import com.tookscan.tookscan.order.domain.Order;
import com.tookscan.tookscan.order.domain.service.OrderService;
import com.tookscan.tookscan.order.repository.mysql.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteAdminOrdersService implements DeleteAdminOrdersUseCase {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @Override
    @Transactional
    public void execute(List<Long> orderIds) {
        List<Order> orders = orderRepository.findAllById(orderIds);

        // 존재하지 않는 주문 ID 확인
        orderService.checkExistOrders(orders, orderIds);

        orderRepository.deleteAll(orders);
    }
}
