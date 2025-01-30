package com.tookscan.tookscan.account.repository.mysql;

import com.tookscan.tookscan.account.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminJpaRepository extends JpaRepository<Admin, UUID>{
}
