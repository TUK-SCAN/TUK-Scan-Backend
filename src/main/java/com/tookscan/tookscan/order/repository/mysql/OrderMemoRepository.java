package com.tookscan.tookscan.order.repository.mysql;

import com.tookscan.tookscan.order.domain.OrderMemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMemoRepository extends JpaRepository<OrderMemo, Long> {
}
