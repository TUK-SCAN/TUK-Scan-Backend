package com.tukscan.tukscan.security.application.service;

import com.tukscan.tukscan.account.domain.Admin;
import com.tukscan.tukscan.account.domain.service.AdminService;
import com.tukscan.tukscan.account.repository.mysql.AdminRepository;
import com.tukscan.tukscan.security.application.dto.request.AdminSignUpDefaultRequestDto;
import com.tukscan.tukscan.security.application.usecase.AdminSignUpDefaultUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminSignUpDefaultService implements AdminSignUpDefaultUseCase {

    private final AdminRepository adminRepository;

    private final AdminService adminService;

    @Override
    @Transactional
    public void execute(AdminSignUpDefaultRequestDto requestDto) {

        // 관리자 생성 및 저장
        Admin admin = adminService.createAdmin(
                requestDto.serialId(),
                requestDto.password()
        );
        adminRepository.save(admin);

    }
}
