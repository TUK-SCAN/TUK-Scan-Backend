package com.tukscan.tukscan.account.domain.service;

import com.tukscan.tukscan.account.domain.Admin;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    public Admin createAdmin(
            String serialId,
            String password
    ) {
        return Admin.builder()
                .serialId(serialId)
                .password(password)
                .build();
    }
}
