package com.tookscan.tookscan.order.domain.type;

import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EDeliveryStatus {
    POST_WAITING("상품준비중"),
    POST_PREPARING("배송준비중"),
    POST_INPROGRESS("배송중"),
    POST_COMPLETED("배송완료"),
    UNKNOWN("알수없음");

    private final String description;

    public static EDeliveryStatus fromString(String value) {
        return switch (value.toUpperCase()) {
            case "POST_WAITING" -> POST_WAITING;
            case "POST_PREPARING" -> POST_PREPARING;
            case "POST_INPROGRESS" -> POST_INPROGRESS;
            case "POST_COMPLETED" -> POST_COMPLETED;
            case "UNKNOWN" -> UNKNOWN;
            default -> throw new CommonException(ErrorCode.INVALID_ENUM_TYPE);
        };
    }

    public static EDeliveryStatus getFrom(EDeliveryDetailStatus value) {
        return switch (value) {
            case INFORMATION_RECEIVED -> POST_WAITING;
            case AT_PICKUP -> POST_PREPARING;
            case IN_TRANSIT, OUT_FOR_DELIVERY -> POST_INPROGRESS;
            case DELIVERED, AVAILABLE_FOR_PICKUP -> POST_COMPLETED;
            case UNKNOWN, EXCEPTION -> UNKNOWN;
        };
    }
}
