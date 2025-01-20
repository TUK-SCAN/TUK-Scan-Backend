package com.tookscan.tookscan.order.application.controller.command;

import com.tookscan.tookscan.core.annotation.security.AccountID;
import com.tookscan.tookscan.core.dto.ResponseDto;
import com.tookscan.tookscan.order.application.dto.request.CreateOrderRequestDto;
import com.tookscan.tookscan.order.application.dto.request.GuestCreateOrderRequestDto;
import com.tookscan.tookscan.order.application.dto.response.CreateOrderResponseDto;
import com.tookscan.tookscan.order.application.dto.response.GuestCreateOrderResponseDto;
import com.tookscan.tookscan.order.application.usecase.CreateOrderUseCase;
import com.tookscan.tookscan.order.application.usecase.GuestCreateOrderUseCase;
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
    private final CreateOrderUseCase createOrderUseCase;
    private final GuestCreateOrderUseCase guestCreateOrderUseCase;
    private final UpdateOrderScanUseCase updateOrderScanUseCase;

    /**
     * 4.1 회원 스캔 주문
     */
    @PostMapping("/users/orders")
    public ResponseDto<CreateOrderResponseDto> createOrder(
            @Parameter(hidden = true) @AccountID UUID accountId,
            @Valid @RequestBody CreateOrderRequestDto requestDto
    ) {
        return ResponseDto.created(createOrderUseCase.execute(accountId, requestDto));
    }

    /**
     * 4.2 비회원 스캔 주문
     */
    @PostMapping("/guests/orders")
    public ResponseDto<GuestCreateOrderResponseDto> createGuestOrder(
            @Valid @RequestBody GuestCreateOrderRequestDto requestDto
    ) {
        return ResponseDto.created(guestCreateOrderUseCase.execute(requestDto));
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
