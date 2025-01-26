package com.tookscan.tookscan.account.repository.mysql.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.UUID;

public interface UserRepositoryCustom {
    Page<UUID> findUserIdsByFilters(String searchType, String search, Long groupId, LocalDate startDate, LocalDate endDate, Pageable pageable);
}
