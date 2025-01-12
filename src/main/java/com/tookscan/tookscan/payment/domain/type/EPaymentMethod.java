package com.tookscan.tookscan.payment.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EPaymentMethod {
    CARD("카드"),
    VERTUAL_ACCOUNT("가상계좌"),
    EASY_PAYMENT("간편결제"),
    ACCOUNT_TRANSFER("계좌이체"),
    GIFT_VOUCHER("상품권");

    private final String name;

    public static EPaymentMethod fromString(String value) {
        return switch (value.toUpperCase()) {
            case "CARD" -> CARD;
            case "VERTUAL_ACCOUNT" -> VERTUAL_ACCOUNT;
            case "EASY_PAYMENT" -> EASY_PAYMENT;
            case "ACCOUNT_TRANSFER" -> ACCOUNT_TRANSFER;
            case "GIFT_VOUCHER" -> GIFT_VOUCHER;
            default -> throw new IllegalArgumentException();
        };
    }
}