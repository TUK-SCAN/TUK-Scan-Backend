package com.tookscan.tookscan.order.application.service;

import com.tookscan.tookscan.order.application.dto.request.UpdateAdminOrdersStatusRequestDto;
import com.tookscan.tookscan.order.application.usecase.UpdateAdminOrdersStatusUseCase;
import com.tookscan.tookscan.order.domain.Order;
import com.tookscan.tookscan.order.repository.OrderRepository;
import java.util.List;
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

        List<Order> orders = orderRepository.findAllByIdOrElseThrow(requestDto.orderIds());

        orders.forEach(order -> order.updateOrderStatus(requestDto.status()));
    }

}