package com.tookscan.tookscan.order.application.admin.controller.command;

import com.tookscan.tookscan.core.annotation.security.AccountID;
import com.tookscan.tookscan.core.dto.ResponseDto;
import com.tookscan.tookscan.order.application.admin.dto.request.CreateOrderMemoRequestDto;
import com.tookscan.tookscan.order.application.admin.usecase.CreateOrderMemoUseCase;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin/orders")
public class OrderCommandV1Controller {

    private final CreateOrderMemoUseCase createOrderMemoUseCase;
    /**
     * 4.5 관리자 주문 메모 작성
     */
     @PostMapping(value = "/{orderId}/memo")
     public ResponseDto<Void> createOrderMemo(
             @Parameter(hidden = true) @AccountID UUID accountId,
             @PathVariable Long orderId,
             @RequestBody @Valid CreateOrderMemoRequestDto requestDto
     ) {
         createOrderMemoUseCase.execute(orderId, requestDto);
         return ResponseDto.ok(null);
     }
}
