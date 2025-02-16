package com.tookscan.tookscan.payment.domain.service;

import com.tookscan.tookscan.order.domain.Order;
import com.tookscan.tookscan.payment.domain.Payment;
import com.tookscan.tookscan.payment.domain.type.EEasyPaymentProvider;
import com.tookscan.tookscan.payment.domain.type.EPaymentMethod;
import com.tookscan.tookscan.payment.domain.type.EPaymentStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {
    public Payment createPayment(
            String paymentKey,
            String type,
            EPaymentMethod method,
            Integer totalAmount,
            EPaymentStatus status,
            LocalDateTime requestedAt,
            EEasyPaymentProvider easyPaymentProvider,
            Order order
    ) {
        return Payment.builder()
                .paymentKey(paymentKey)
                .type(type)
                .method(method)
                .totalAmount(totalAmount)
                .status(status)
                .requestedAt(requestedAt)
                .approvedAt(LocalDateTime.now())
                .easyPaymentProvider(easyPaymentProvider)
                .order(order)
                .build();
    }
}
