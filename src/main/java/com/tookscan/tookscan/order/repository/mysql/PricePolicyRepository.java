package com.tookscan.tookscan.order.repository.mysql;

import com.tookscan.tookscan.order.domain.PricePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricePolicyRepository extends JpaRepository<PricePolicy, Long> {
}
