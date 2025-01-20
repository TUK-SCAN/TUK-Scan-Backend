package com.tookscan.tookscan.order.application.controller.command;

import com.tookscan.tookscan.core.annotation.security.AccountID;
import com.tookscan.tookscan.core.dto.ResponseDto;
import com.tookscan.tookscan.order.application.dto.request.CreateUserOrderRequestDto;
import com.tookscan.tookscan.order.application.dto.request.CreateGuestOrderRequestDto;
import com.tookscan.tookscan.order.application.dto.response.CreateUserOrderResponseDto;
import com.tookscan.tookscan.order.application.dto.response.CreateGuestOrderResponseDto;
import com.tookscan.tookscan.order.application.usecase.CreateUserOrderUseCase;
import com.tookscan.tookscan.order.application.usecase.CreateGuestOrderUseCase;
import com.tookscan.tookscan.order.application.usecase.UpdateOrderScanUseCase;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class OrderCommandV1Controller {
    private final CreateUserOrderUseCase createUserOrderUseCase;
    private final CreateGuestOrderUseCase createGuestOrderUseCase;
    private final UpdateOrderScanUseCase updateOrderScanUseCase;

    /**
     * 4.1 회원 스캔 주문
     */
    @PostMapping("/users/orders")
    public ResponseDto<CreateUserOrderResponseDto> createOrder(
            @Parameter(hidden = true) @AccountID UUID accountId,
            @Valid @RequestBody CreateUserOrderRequestDto requestDto
    ) {
        return ResponseDto.created(createUserOrderUseCase.execute(accountId, requestDto));
    }

    /**
     * 4.2 비회원 스캔 주문
     */
    @PostMapping("/guests/orders")
    public ResponseDto<CreateGuestOrderResponseDto> createGuestOrder(
            @Valid @RequestBody CreateGuestOrderRequestDto requestDto
    ) {
        return ResponseDto.created(createGuestOrderUseCase.execute(requestDto));
    }

    /**
     * 4.4 회원 스캔하기
     */
    @PatchMapping(value = "/users/orders/{orderId}/scan")
    public ResponseDto<Void> updateOrderScan(
            @Parameter(hidden = true) @AccountID UUID accountId,
            @PathVariable Long orderId
    ) {
        updateOrderScanUseCase.execute(accountId, orderId);
        return ResponseDto.ok(null);
    }

}
