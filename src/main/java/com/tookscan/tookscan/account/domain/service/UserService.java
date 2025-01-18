package com.tookscan.tookscan.account.domain.service;

import com.tookscan.tookscan.account.application.dto.request.UpdateUserRequestDto;
import com.tookscan.tookscan.account.domain.User;
import com.tookscan.tookscan.security.domain.type.ESecurityProvider;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User createUser(
            ESecurityProvider provider,
            String serialId,
            String password,
            String name,
            String phoneNumber,
            Boolean marketingAllowed
    ) {
        return User.builder()
                .provider(provider)
                .serialId(serialId)
                .password(password)
                .name(name)
                .phoneNumber(phoneNumber)
                .marketingAllowed(marketingAllowed)
                .build();
    }

    public boolean isPhoneNumberChanged(
            User user,
            String phoneNumber
    ) {
        return !user.getPhoneNumber().equals(phoneNumber);
    }

    public User updateSelf(
            User user,
            UpdateUserRequestDto requestDto
    ) {
        user.updateEmail(requestDto.email());
        user.updatePhone(requestDto.phoneNumber());
        user.updateAddress(requestDto.address().toEntity());
        return user;
    }
}
