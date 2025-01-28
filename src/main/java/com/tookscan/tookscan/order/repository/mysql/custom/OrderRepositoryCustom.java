package com.tookscan.tookscan.order.repository.mysql.custom;

import com.tookscan.tookscan.order.domain.type.EOrderStatus;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepositoryCustom {
    Map<EOrderStatus, Integer> findOrderStatusCounts();

    Page<Long> findOrderSummaries(Integer page, Integer size, String startDate, String endDate, String search, String searchType, String sort, String direction, Pageable pageable);
}
