package com.tookscan.tookscan.order.application.service;

import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import com.tookscan.tookscan.order.application.dto.request.UpdateAdminOrdersStatusRequestDto;
import com.tookscan.tookscan.order.application.usecase.UpdateAdminOrdersStatusUseCase;
import com.tookscan.tookscan.order.domain.Order;
import com.tookscan.tookscan.order.repository.mysql.OrderRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateAdminOrdersStatusService implements UpdateAdminOrdersStatusUseCase {

    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public void execute(UpdateAdminOrdersStatusRequestDto requestDto) {

        List<Order> orders = orderRepository.findAllById(requestDto.orderIds());

        // 존재하지 않는 주문 ID 확인
        if (orders.size() != requestDto.orderIds().size()) {
            Set<Long> foundIds = orders.stream()
                    .map(Order::getId)
                    .collect(Collectors.toSet());

            List<Long> notFoundIds = requestDto.orderIds().stream()
                    .filter(id -> !foundIds.contains(id))
                    .toList();

            throw new CommonException(ErrorCode.NOT_FOUND_ORDER, "주문 ID: " + notFoundIds.toString());
        }

        requestDto.orderIds().forEach(orderId -> {
            orderRepository.findById(orderId)
                    .ifPresent(order -> order.updateStatus(requestDto.status()));
        });
    }

}