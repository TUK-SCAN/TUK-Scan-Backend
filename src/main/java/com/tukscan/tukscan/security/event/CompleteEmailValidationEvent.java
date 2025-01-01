package com.tukscan.tukscan.security.event;

import lombok.Builder;

@Builder
public record CompleteEmailValidationEvent(
        String receiverAddress,
        String authenticationCode
) {
    public static CompleteEmailValidationEvent of(String receiverAddress, String authenticationCode) {
        return CompleteEmailValidationEvent.builder()
                .receiverAddress(receiverAddress)
                .authenticationCode(authenticationCode)
                .build();
    }
}
