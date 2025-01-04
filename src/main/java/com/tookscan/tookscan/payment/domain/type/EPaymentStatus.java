package com.tookscan.tookscan.payment.domain.type;

import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EPaymentStatus {
    READY("결제대기"),
    IN_PROGRESS("결제중"),
    WAITING_FOR_DEPOSIT("입금대기"),
    DONE("결제완료"),
    CANCELED("결제취소"),
    PARTIAL_CANCELED("부분취소"),
    ABORTED("결제중단"),
    EXPIRED("결제만료");

    private final String description;

    public static EPaymentStatus fromString(String value) {
        return switch (value.toUpperCase()) {
            case "READY" -> READY;
            case "IN_PROGRESS" -> IN_PROGRESS;
            case "WAITING_FOR_DEPOSIT" -> WAITING_FOR_DEPOSIT;
            case "DONE" -> DONE;
            case "CANCELED" -> CANCELED;
            case "PARTIAL_CANCELED" -> PARTIAL_CANCELED;
            case "ABORTED" -> ABORTED;
            case "EXPIRED" -> EXPIRED;
            default -> throw new CommonException(ErrorCode.INVALID_ENUM_TYPE);
        };
    }

}
