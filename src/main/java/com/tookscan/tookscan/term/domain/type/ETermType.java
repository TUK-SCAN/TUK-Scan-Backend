package com.tookscan.tookscan.term.domain.type;

import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ETermType {
    SIGN_UP("회원가입"),
    SCAN("스캔");

    private final String description;

    public static ETermType fromString(String value) {
        return switch (value.toUpperCase()) {
            case "SIGN_UP" -> SIGN_UP;
            case "SCAN" -> SCAN;
            default -> throw new CommonException(ErrorCode.INVALID_ENUM_TYPE);
        };
    }
}
