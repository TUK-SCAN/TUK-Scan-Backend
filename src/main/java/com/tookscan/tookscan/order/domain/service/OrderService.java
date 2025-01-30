package com.tookscan.tookscan.order.domain.service;

import com.tookscan.tookscan.account.domain.User;
import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import com.tookscan.tookscan.core.infrastructure.TsidFactory;
import com.tookscan.tookscan.order.domain.Delivery;
import com.tookscan.tookscan.order.domain.Order;
import com.tookscan.tookscan.order.domain.type.EOrderStatus;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    public Order createOrder(User user, boolean isByUser, Delivery delivery) {
        String orderNumber = TsidFactory.getFactory().generate().toString();
        return Order.builder()
                .orderNumber(orderNumber)
                .orderStatus(EOrderStatus.APPLY_COMPLETED)
                .isByUser(isByUser)
                .user(user)
                .delivery(delivery)
                .build();
    }

    public void updateOrderStatus(Order order, EOrderStatus newOrderStatus) {
        EOrderStatus oldOrderStatus = order.getOrderStatus();
        order.updateOrderStatus(newOrderStatus);
        log.info("Order status changed. OrderNumber: {}, oldStatus: {}, newStatus: {}", order.getOrderNumber(), oldOrderStatus, newOrderStatus);
    }

    public void checkExistOrders(List<Order> orders, List<Long> orderIds) {
        if (orders.size() != orderIds.size()) {
            Set<Long> foundIds = orders.stream()
                    .map(Order::getId)
                    .collect(Collectors.toSet());

            List<Long> notFoundIds = orderIds.stream()
                    .filter(id -> !foundIds.contains(id))
                    .toList();

            throw new CommonException(ErrorCode.NOT_FOUND_ORDER, "주문 ID: " + notFoundIds);
        }
    }

    public void validateOrderUser(Order order, User user) {
        if (!order.getUser().getId().equals(user.getId())) {
            throw new CommonException(ErrorCode.ACCESS_DENIED);
        }
    }

    public void validateOrderNumber(Order order, String name, String orderNumber) {
        if (!order.getDelivery().getReceiverName().equals(name) || !order.getOrderNumber().equals(orderNumber)) {
            throw new CommonException(ErrorCode.ACCESS_DENIED);
        }
    }

    public void validateOrderStatus(Order order, EOrderStatus status, ErrorCode errorCode) {
        if (!order.getOrderStatus().equals(status)) {
            throw new CommonException(errorCode);
        }
    }
}
