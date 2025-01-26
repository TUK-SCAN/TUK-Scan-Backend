package com.tookscan.tookscan.order.application.guest.controller.command;

import com.tookscan.tookscan.core.dto.ResponseDto;
import com.tookscan.tookscan.order.application.guest.dto.request.CreateOrderRequestDto;
import com.tookscan.tookscan.order.application.guest.dto.response.CreateOrderResponseDto;
import com.tookscan.tookscan.order.application.guest.usecase.CreateOrderUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/guest/orders")
public class OrderCommandV1Controller {

    private final CreateOrderUseCase createOrderUseCase;

    /**
     * 4.2 비회원 스캔 주문
     */
    @PostMapping()
    public ResponseDto<CreateOrderResponseDto> createOrder(
            @RequestBody @Valid CreateOrderRequestDto requestDto
    ) {
        return ResponseDto.created(createOrderUseCase.execute(requestDto));
    }
}
