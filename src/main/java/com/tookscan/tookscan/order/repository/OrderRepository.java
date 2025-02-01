package com.tookscan.tookscan.order.repository;

import com.tookscan.tookscan.account.domain.User;
import com.tookscan.tookscan.order.domain.Order;
import com.tookscan.tookscan.order.domain.type.EOrderStatus;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepository {

    void save(Order order);

    Order findByIdOrElseThrow(Long id);

    List<Order> findAllByIdOrElseThrow(List<Long> ids);

    Map<EOrderStatus, Integer> findOrderStatusCounts();

    Page<Long> findOrderSummaries(String startDate, String endDate, String search, String searchType, String sort,
                                  String direction, Pageable pageable);

    void deleteAll(List<Order> orders);

    Page<Order> findAllByUserAndSearch(User user, String search, Pageable pageable);

    Order findByOrderNumberOrElseThrow(String orderNumber);

    List<Order> findAllByUserIds(List<UUID> userIds);

    List<Order> findAllWithDocumentsByIdIn(List<Long> ids);

    List<Order> findAllByOrderNumberIn(List<String> orderNumber);
}
