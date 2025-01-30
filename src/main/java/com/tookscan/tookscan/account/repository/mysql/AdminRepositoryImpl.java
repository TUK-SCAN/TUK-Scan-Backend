package com.tookscan.tookscan.account.repository.mysql;

import com.tookscan.tookscan.account.domain.Admin;
import com.tookscan.tookscan.account.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminRepositoryImpl implements AdminRepository {

    private final AdminJpaRepository adminJpaRepository;

    @Override
    public void save(Admin admin) {
        adminJpaRepository.save(admin);
    }
}
