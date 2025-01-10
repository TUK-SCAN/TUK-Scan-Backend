package com.tookscan.tookscan.order.application.controller.command;

import com.tookscan.tookscan.core.annotation.security.AccountID;
import com.tookscan.tookscan.core.dto.ResponseDto;
import com.tookscan.tookscan.order.application.dto.request.CreateOrderRequestDto;
import com.tookscan.tookscan.order.application.dto.response.CreateOrderResponseDto;
import com.tookscan.tookscan.order.application.usecase.CreateOrderUseCase;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
@Slf4j
public class OrderCommandV1Controller {
    private final CreateOrderUseCase createOrderUseCase;

    /**
     * 4.1 회원 스캔 주문하기
     */
    @PostMapping(value = "/users/orders")
    public ResponseDto<CreateOrderResponseDto> createOrder(
            @AccountID UUID accountId,
            @RequestBody @Valid CreateOrderRequestDto requestDto
    ) {
        return ResponseDto.created(createOrderUseCase.execute(accountId, requestDto));
    }


}
