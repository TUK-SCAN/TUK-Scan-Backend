package com.tukscan.tukscan.security.domain.service;

import com.tukscan.tukscan.core.exception.error.ErrorCode;
import com.tukscan.tukscan.core.exception.type.CommonException;
import com.tukscan.tukscan.security.domain.mysql.Account;
import com.tukscan.tukscan.security.domain.type.ESecurityRole;
import com.tukscan.tukscan.security.info.CustomUserPrincipal;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    public void changePassword(Account account, String newPassword) {
        account.updatePassword(newPassword);
    }

    public void checkPassword(Account account, String password) {
        if (!account.checkPassword(password))
            throw new CommonException(ErrorCode.INVALID_ARGUMENT);
    }

    public CustomUserPrincipal createCustomUserPrincipalByAccount(Account account) {
        return CustomUserPrincipal.create(account);
    }

    public Account updatePhoneNumber(Account account, String phoneNumber) {
        account.updatePhoneNumber(phoneNumber);
        return account;
    }

    public void checkUserValidation(Account account) {
        if (!account.getRole().equals(ESecurityRole.USER))
            throw new CommonException(ErrorCode.INVALID_ACCOUNT_TYPE);
    }

    public void checkAdminValidation(Account account) {
        if (!account.getRole().equals(ESecurityRole.ADMIN))
            throw new CommonException(ErrorCode.INVALID_ACCOUNT_TYPE);
    }
}
