package com.tookscan.tookscan.order.domain.service;

import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import com.tookscan.tookscan.order.domain.PricePolicy;
import com.tookscan.tookscan.order.domain.type.ERecoveryOption;
import com.tookscan.tookscan.order.repository.mysql.PricePolicyRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PricePolicyService {

    private final PricePolicyRepository pricePolicyRepository;

    public int calculatePrice(int pageCount, ERecoveryOption eRecoveryOption) {
        LocalDate now = LocalDate.now();
        System.out.println("now = " + now);
        PricePolicy pricePolicy = pricePolicyRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(now, now);
        if (pricePolicy == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_RESOURCE);
        }
        return pricePolicy.calculatePrice(pageCount, eRecoveryOption);
    }

}
