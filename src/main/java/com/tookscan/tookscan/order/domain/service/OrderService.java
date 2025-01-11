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

    /**
     *  TODO: 동시성 문제 개선 필요
     *  현재 구현은 단일 스레드 환경에서는 문제가 없으나, 다중 스레드 환경에서는 동일한 주문 번호가 생성될 가능성이 있습니다.
     *  대안 1: Redis의 INCR 명령어를 사용하여 하루 동안 유일한 증가값을 생성.
     *    예: "order_number:{날짜}" 키를 Redis에서 관리하여 INCR 명령어로 증가된 값을 사용.
     *  대안 2: 분산 ID 생성기(Snowflake) 또는 UUID 기반의 유니크한 ID를 생성.
     *  추후 동시성 문제를 해결하기 위한 개선 작업이 필요합니다.
     */
    public long getTodayOrderCount() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1); // 하루의 마지막 순간
        return orderRepository.countByCreatedAtBetween(startOfDay, endOfDay);
    }

    public void updateOrderStatus(Order order, EOrderStatus orderStatus) {
        order.updateOrderStatus(orderStatus);
    }

}
