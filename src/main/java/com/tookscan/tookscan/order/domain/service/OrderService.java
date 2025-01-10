package com.tookscan.tookscan.order.domain.service;

import com.tookscan.tookscan.account.domain.User;
import com.tookscan.tookscan.core.utility.DateTimeUtil;
import com.tookscan.tookscan.order.domain.Order;
import com.tookscan.tookscan.order.domain.type.EOrderStatus;
import com.tookscan.tookscan.order.repository.mysql.OrderRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order createOrder(User user, Long orderNumber, boolean isByUser, String orderPassword) {
        return Order.builder()
                .orderNumber(orderNumber)
                .orderStatus(EOrderStatus.APPLY_COMPLETED)
                .isByUser(isByUser)
                .orderPassword(orderPassword)
                .user(user)
                .build();
    }

    public Long createOrderNumber() {
        String yyMMdd = DateTimeUtil.convertLocalDateToYYMMDDString(LocalDate.now());
        String orderNumber = yyMMdd + (getTodayOrderCount() + 1);

        return Long.parseLong(orderNumber);
    }

    public long getTodayOrderCount() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1); // 하루의 마지막 순간
        return orderRepository.countByCreatedAtBetween(startOfDay, endOfDay);
    }

}
