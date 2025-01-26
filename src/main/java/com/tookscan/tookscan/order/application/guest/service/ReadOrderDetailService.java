package com.tookscan.tookscan.order.application.guest.service;

import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import com.tookscan.tookscan.order.application.guest.dto.response.ReadOrderDetailResponseDto;
import com.tookscan.tookscan.order.application.guest.usecase.ReadOrderDetailUseCase;
import com.tookscan.tookscan.order.domain.Order;
import com.tookscan.tookscan.order.domain.service.OrderService;
import com.tookscan.tookscan.order.repository.mysql.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReadOrderDetailService implements ReadOrderDetailUseCase {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @Override
    @Transactional(readOnly = true)
    public ReadOrderDetailResponseDto execute(String name, Long orderNumber) {

        // 주문 조회
        Order order = orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ORDER));

        // 주문자 확인
        orderService.validateOrderNumber(order, name, orderNumber);

        return ReadOrderDetailResponseDto.fromEntity(order);

    }
}
