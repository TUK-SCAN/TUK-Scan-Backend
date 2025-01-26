package com.tookscan.tookscan.order.application.user.controller.command;

import com.tookscan.tookscan.core.annotation.security.AccountID;
import com.tookscan.tookscan.core.dto.ResponseDto;
import com.tookscan.tookscan.order.application.user.dto.request.CreateUserOrderRequestDto;
import com.tookscan.tookscan.order.application.user.dto.response.CreateUserOrderResponseDto;
import com.tookscan.tookscan.order.application.user.usecase.CreateUserOrderUseCase;
import com.tookscan.tookscan.order.application.user.usecase.UpdateUserOrderScanUseCase;
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
@RequestMapping("/v1/users/orders")
public class OrderUserCommandV1Controller {
    private final CreateUserOrderUseCase createUserOrderUseCase;
    private final UpdateUserOrderScanUseCase updateUserOrderScanUseCase;

    /**
     * 4.1 회원 스캔 주문
     */
    @PostMapping()
    public ResponseDto<CreateUserOrderResponseDto> createOrder(
            @Parameter(hidden = true) @AccountID UUID accountId,
            @RequestBody @Valid CreateUserOrderRequestDto requestDto
    ) {
        return ResponseDto.created(createUserOrderUseCase.execute(accountId, requestDto));
    }

    /**
     * 4.4 회원 스캔하기
     */
    @PatchMapping(value = "/{orderId}/scan")
    public ResponseDto<Void> updateOrderScan(
            @Parameter(hidden = true) @AccountID UUID accountId,
            @PathVariable Long orderId
    ) {
        updateUserOrderScanUseCase.execute(accountId, orderId);
        return ResponseDto.ok(null);
    }
}
