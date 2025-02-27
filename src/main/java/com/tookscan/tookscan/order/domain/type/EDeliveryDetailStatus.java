package com.tookscan.tookscan.order.domain.type;

import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EDeliveryDetailStatus {
    UNKNOWN("알수없음"),
    INFORMATION_RECEIVED("상품준비중"),
    AT_PICKUP("픽업준비중"),
    IN_TRANSIT("배송중"),
    OUT_FOR_DELIVERY("배송출발"),
    DELIVERED("배송완료"),
    AVAILABLE_FOR_PICKUP("픽업가능"),
    EXCEPTION("배송오류");

    private final String description;

    public static EDeliveryDetailStatus fromString(String value) {
        return switch (value.toUpperCase()) {
            case "UNKNOWN" -> UNKNOWN;
            case "INFORMATION_RECEIVED" -> INFORMATION_RECEIVED;
            case "AT_PICKUP" -> AT_PICKUP;
            case "IN_TRANSIT" -> IN_TRANSIT;
            case "OUT_FOR_DELIVERY" -> OUT_FOR_DELIVERY;
            case "DELIVERED" -> DELIVERED;
            case "AVAILABLE_FOR_PICKUP" -> AVAILABLE_FOR_PICKUP;
            case "EXCEPTION" -> EXCEPTION;
            default -> throw new CommonException(ErrorCode.INVALID_ENUM_TYPE);
        };
    }
}
