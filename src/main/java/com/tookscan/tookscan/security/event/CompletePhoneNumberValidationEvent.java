package com.tookscan.tookscan.security.event;

import lombok.Builder;

@Builder
public record CompletePhoneNumberValidationEvent(
        String receiverAddress,
        String authenticationCode
) {
    public static CompletePhoneNumberValidationEvent of(String receiverAddress, String authenticationCode) {
        return CompletePhoneNumberValidationEvent.builder()
                .receiverAddress(receiverAddress)
                .authenticationCode(authenticationCode)
                .build();
    }
}
