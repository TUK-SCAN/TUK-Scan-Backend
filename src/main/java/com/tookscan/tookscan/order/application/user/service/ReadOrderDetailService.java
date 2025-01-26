package com.tookscan.tookscan.order.application.user.service;

import com.tookscan.tookscan.account.domain.User;
import com.tookscan.tookscan.account.repository.mysql.UserRepository;
import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import com.tookscan.tookscan.order.application.user.dto.response.ReadOrderDetailResponseDto;
import com.tookscan.tookscan.order.application.user.usecase.ReadOrderDetailUseCase;
import com.tookscan.tookscan.order.domain.Order;
import com.tookscan.tookscan.order.domain.service.OrderService;
import com.tookscan.tookscan.order.repository.mysql.OrderRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReadOrderDetailService implements ReadOrderDetailUseCase {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @Override
    @Transactional(readOnly = true)
    public ReadOrderDetailResponseDto execute(UUID accountID, Long orderId) {
        // 사용자 조회
        User user = userRepository.findById(accountID)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ACCOUNT));

        // 주문 조회
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ORDER));

        // 주문자 확인
        orderService.validateOrderUser(order, user);

        return ReadOrderDetailResponseDto.fromEntity(order);

    }
}
