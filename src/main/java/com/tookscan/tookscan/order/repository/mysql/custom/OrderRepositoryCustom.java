package com.tookscan.tookscan.order.repository.mysql.custom;

import com.tookscan.tookscan.order.domain.type.EOrderStatus;
import java.util.Map;

public interface OrderRepositoryCustom {
    Map<EOrderStatus, Integer> findOrderStatusCounts();
}
