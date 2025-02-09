package com.tookscan.tookscan.payment.repository;

import java.time.LocalDateTime;

public interface PaymentRepository {
    Integer sumTotalAmountByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
