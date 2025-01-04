package com.tookscan.tookscan.message.domain.type;

import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EMessageStatus {
    TEMPORARY_SAVED("임시저장"),
    CANCELED("취소됨"),
    RESERVED("예약됨"),
    COMPLETED("완료됨");

    private final String description;

    public static EMessageStatus fromString(String value) {
        return switch (value.toUpperCase()) {
            case "TEMPORARY_SAVED" -> TEMPORARY_SAVED;
            case "CANCELD" -> CANCELED;
            case "RESERVED" -> RESERVED;
            case "COMPLETED" -> COMPLETED;
            default -> throw new CommonException(ErrorCode.INVALID_ENUM_TYPE);
        };

    }
}
