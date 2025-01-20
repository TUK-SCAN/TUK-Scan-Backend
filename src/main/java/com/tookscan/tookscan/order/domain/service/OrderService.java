package com.tookscan.tookscan.order.domain.service;

import com.tookscan.tookscan.account.domain.User;
import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import com.tookscan.tookscan.order.domain.Delivery;
import com.tookscan.tookscan.order.domain.Order;
import com.tookscan.tookscan.order.domain.PricePolicy;
import com.tookscan.tookscan.order.domain.type.EOrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    public Order createOrder(User user, Long orderNumber, boolean isByUser, String orderPassword, Delivery delivery, PricePolicy pricePolicy) {
        return Order.builder()
                .orderNumber(orderNumber)
                .orderStatus(EOrderStatus.APPLY_COMPLETED)
                .isByUser(isByUser)
                .orderPassword(orderPassword)
                .user(user)
                .delivery(delivery)
                .pricePolicy(pricePolicy)
                .build();
    }

    public void updateOrderStatus(Order order, EOrderStatus newOrderStatus) {
        EOrderStatus oldOrderStatus = order.getOrderStatus();
        order.updateOrderStatus(newOrderStatus);
        log.info("Order status changed. OrderNumber: {}, oldStatus: {}, newStatus: {}", order.getOrderNumber(), oldOrderStatus, newOrderStatus);
    }

    public void validateOrderUser(Order order, User user) {
        if (!order.getUser().getId().equals(user.getId())) {
            throw new CommonException(ErrorCode.ACCESS_DENIED);
        }
    }

    public void validateOrderStatus(Order order, EOrderStatus status, ErrorCode errorCode) {
        if (!order.getOrderStatus().equals(status)) {
            throw new CommonException(errorCode);
        }
    }
}
