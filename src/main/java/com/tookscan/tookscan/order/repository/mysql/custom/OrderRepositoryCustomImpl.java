package com.tookscan.tookscan.order.repository.mysql.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tookscan.tookscan.order.domain.QOrder;
import com.tookscan.tookscan.order.domain.type.EOrderStatus;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Map<EOrderStatus, Integer> findOrderStatusCounts() {
        QOrder order = QOrder.order;

        Map<EOrderStatus, Integer> statusCounts = jpaQueryFactory
                .select(order.orderStatus, order.count().intValue())
                .from(order)
                .groupBy(order.orderStatus)
                .fetch()
                .stream()
                .collect(Collectors.toMap(
                        tuple -> tuple.get(order.orderStatus),
                        tuple -> tuple.get(order.count().intValue())
                ));

        for (EOrderStatus status : EOrderStatus.values()) {
            statusCounts.putIfAbsent(status, 0);
        }

        return statusCounts;
    }
}
