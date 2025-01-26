package com.tookscan.tookscan.order.application.admin.service;

import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import com.tookscan.tookscan.order.application.admin.dto.request.CreateOrderMemoRequestDto;
import com.tookscan.tookscan.order.application.admin.usecase.CreateOrderMemoUseCase;
import com.tookscan.tookscan.order.domain.Order;
import com.tookscan.tookscan.order.repository.mysql.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateOrderMemoService implements CreateOrderMemoUseCase {

    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public void execute(Long orderId, CreateOrderMemoRequestDto requestDto) {
        // 주문 메모 생성
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ORDER));
        order.createMemo(requestDto.content());
    }
}
