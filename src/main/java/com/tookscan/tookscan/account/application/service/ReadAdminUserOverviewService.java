package com.tookscan.tookscan.account.application.service;

import com.tookscan.tookscan.account.application.dto.response.ReadAdminUserOverviewResponseDto;
import com.tookscan.tookscan.account.application.usecase.ReadAdminUserOverviewUseCase;
import com.tookscan.tookscan.account.domain.User;
import com.tookscan.tookscan.account.repository.mysql.UserRepository;
import com.tookscan.tookscan.core.dto.PageInfoDto;
import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import com.tookscan.tookscan.order.domain.Order;
import com.tookscan.tookscan.order.repository.mysql.OrderRepository;
import com.tookscan.tookscan.security.domain.mysql.Account;
import com.tookscan.tookscan.security.domain.service.AccountService;
import com.tookscan.tookscan.security.repository.mysql.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReadAdminUserOverviewService implements ReadAdminUserOverviewUseCase {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    private final AccountService accountService;

    @Override
    @Transactional(readOnly = true)
    public ReadAdminUserOverviewResponseDto execute(UUID accountId, String search, Long groupId, LocalDate startDate, LocalDate endDate, Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page - 1, size);

        // Account 조회
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ACCOUNT));

        // 관리자 권한 확인
        accountService.checkAdminValidation(account);

        // 페이지네이션 된 UserProjection 조회
        Page<UserRepository.UserProjection> userProjections = userRepository.findUserProjectionsByFilters(search, groupId, startDate, endDate, pageable);

        // userId 리스트 추출
        List<UUID> userIds = userProjections.stream()
                .map(UserRepository.UserProjection::getUserId)
                .toList();

        // Order 리스트 조회
        List<Order> orders = orderRepository.findAllByUserIds(userIds);

        // User 리스트 조회
        List<User> users = userRepository.findUserByIdsWithDetails(userIds);

        return ReadAdminUserOverviewResponseDto.of(users, orders, PageInfoDto.fromEntity(userProjections));
    }
}
