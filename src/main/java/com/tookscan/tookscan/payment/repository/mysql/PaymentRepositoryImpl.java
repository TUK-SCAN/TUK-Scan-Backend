package com.tookscan.tookscan.payment.repository.mysql;

import com.tookscan.tookscan.payment.repository.PaymentRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {
    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public Integer sumTotalAmountByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return paymentJpaRepository.sumTotalAmountByCreatedAtBetween(startDate, endDate);
    }
}
