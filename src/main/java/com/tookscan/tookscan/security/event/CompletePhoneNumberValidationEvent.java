package com.tookscan.tookscan.security.event;

public record CompletePhoneNumberValidationEvent(
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
