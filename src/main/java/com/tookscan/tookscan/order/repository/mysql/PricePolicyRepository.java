package com.tookscan.tookscan.order.repository.mysql;

import aj.org.objectweb.asm.commons.Remapper;
import com.tookscan.tookscan.order.domain.PricePolicy;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricePolicyRepository extends JpaRepository<PricePolicy, Long> {

    PricePolicy findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate now1, LocalDate now2);
}
