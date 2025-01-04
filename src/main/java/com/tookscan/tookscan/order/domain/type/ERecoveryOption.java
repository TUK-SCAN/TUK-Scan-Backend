package com.tookscan.tookscan.order.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ERecoveryOption {
    DISCARD("폐기"),
    RAW("원본"),
    SPRING("스프링");

    private final String description;

    public static ERecoveryOption fromString(String value) {
        return switch (value.toUpperCase()) {
            case "DISCARD" -> DISCARD;
            case "RAW" -> RAW;
            case "SPRING" -> SPRING;
            default -> throw new IllegalArgumentException();
        };
    }
}
