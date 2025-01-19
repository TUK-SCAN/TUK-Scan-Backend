package com.tookscan.tookscan.account.domain.service;

import com.tookscan.tookscan.account.domain.User;
import com.tookscan.tookscan.address.dto.request.AddressRequestDto;
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
            String email,
            String phoneNumber,
            AddressRequestDto addressDto
    ) {
        user.updateEmail(email);
        user.updatePhone(phoneNumber);
        user.updateAddress(addressDto.toEntity());
        return user;
    }
}
