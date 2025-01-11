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
        PricePolicy pricePolicy = pricePolicyRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(now, now);

        return pricePolicy.calculatePrice(pageCount, eRecoveryOption);
    }

    private void validatePricePolicy(PricePolicy pricePolicy) {
        // 가격 정책이 없거나 시작일이 종료일보다 늦을 경우
        if (pricePolicy == null) {
            throw new CommonException(ErrorCode.INTERNAL_DATA_ERROR);
        }
        if (pricePolicy.getStartDate().isAfter(pricePolicy.getEndDate())) {
            throw new CommonException(ErrorCode.INTERNAL_DATA_ERROR);
        }
    }

    private void validatePageCount(int pageCount) {
        // 페이지 수가 0 이하일 경우
        if (pageCount <= 0) {
            throw new CommonException(ErrorCode.INVALID_ARGUMENT);
        }
    }
}
