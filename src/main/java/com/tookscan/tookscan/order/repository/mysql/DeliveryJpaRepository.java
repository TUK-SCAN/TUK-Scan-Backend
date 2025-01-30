package com.tookscan.tookscan.order.repository.mysql;

import com.tookscan.tookscan.order.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryJpaRepository extends JpaRepository<Delivery, Long> {
}
