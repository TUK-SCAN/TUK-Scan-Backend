package com.tookscan.tookscan.order.application.user.controller.command;

import com.tookscan.tookscan.core.dto.ResponseDto;
import com.tookscan.tookscan.order.application.user.dto.request.CreateGuestOrderRequestDto;
import com.tookscan.tookscan.order.application.user.dto.response.CreateGuestOrderResponseDto;
import com.tookscan.tookscan.order.application.user.usecase.CreateGuestOrderUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order", description = "Order 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/guests/orders")
public class OrderGuestCommandV1Controller {

    private final CreateGuestOrderUseCase createGuestOrderUseCase;

    /**
     * 4.2 비회원 스캔 주문
     */
    @Operation(summary = "비회원 스캔 주문", description = "비회원이 주문을 생성합니다.")
    @PostMapping()
    public ResponseDto<CreateGuestOrderResponseDto> createOrder(
            @RequestBody @Valid CreateGuestOrderRequestDto requestDto
    ) {
        return ResponseDto.created(createGuestOrderUseCase.execute(requestDto));
    }
}
