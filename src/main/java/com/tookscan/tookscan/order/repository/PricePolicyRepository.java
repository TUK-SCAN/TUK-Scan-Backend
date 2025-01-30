package com.tookscan.tookscan.order.repository;

import com.tookscan.tookscan.order.domain.PricePolicy;
import java.time.LocalDate;

public interface PricePolicyRepository {
    PricePolicy findByStartDateLessThanEqualAndEndDateGreaterThanEqualOrElseThrow(LocalDate now1, LocalDate now2);
}
