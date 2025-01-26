package com.tookscan.tookscan.order.application.user.controller.command;

import com.tookscan.tookscan.core.dto.ResponseDto;
import com.tookscan.tookscan.order.application.user.dto.request.CreateGuestOrderRequestDto;
import com.tookscan.tookscan.order.application.user.dto.response.CreateGuestOrderResponseDto;
import com.tookscan.tookscan.order.application.user.usecase.CreateGuestOrderUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/guest/orders")
public class OrderGuestCommandV1Controller {

    private final CreateGuestOrderUseCase createGuestOrderUseCase;

    /**
     * 4.2 비회원 스캔 주문
     */
    @PostMapping()
    public ResponseDto<CreateGuestOrderResponseDto> createOrder(
            @RequestBody @Valid CreateGuestOrderRequestDto requestDto
    ) {
        return ResponseDto.created(createGuestOrderUseCase.execute(requestDto));
    }
}
