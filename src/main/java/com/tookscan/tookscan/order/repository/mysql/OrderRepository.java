package com.tookscan.tookscan.order.repository.mysql;

import com.tookscan.tookscan.order.domain.Order;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    long countByCreatedAtBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
