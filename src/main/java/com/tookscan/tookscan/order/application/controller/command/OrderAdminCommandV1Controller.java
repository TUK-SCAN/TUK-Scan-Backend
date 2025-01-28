package com.tookscan.tookscan.order.application.controller.command;

import com.tookscan.tookscan.core.dto.ResponseDto;
import com.tookscan.tookscan.order.application.dto.request.CreateAdminOrderMemoRequestDto;
import com.tookscan.tookscan.order.application.dto.request.UpdateAdminOrdersStatusRequestDto;
import com.tookscan.tookscan.order.application.usecase.CreateAdminOrderMemoUseCase;
import com.tookscan.tookscan.order.application.usecase.UpdateAdminOrdersStatusUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order", description = "Order 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admins/orders")
public class OrderAdminCommandV1Controller {

    private final CreateAdminOrderMemoUseCase createAdminOrderMemoUseCase;
    private final UpdateAdminOrdersStatusUseCase updateAdminOrdersStatusUseCase;

    /**
     * 4.5 관리자 주문 메모 작성
     */
    @Operation(summary = "관리자 주문 메모 작성", description = "관리자가 주문에 메모를 작성합니다.")
    @PostMapping(value = "/{orderId}/memo")
    public ResponseDto<Void> createOrderMemo(
         @PathVariable Long orderId,
         @RequestBody @Valid CreateAdminOrderMemoRequestDto requestDto
    ) {
     createAdminOrderMemoUseCase.execute(orderId, requestDto);
     return ResponseDto.ok(null);
    }

    /**
     * 4.3.2 관리자 주문 상태 일괄 변경
     */
    @Operation(summary = "관리자 주문 상태 일괄 변경", description = "관리자가 여러 주문의 상태를 일괄 변경합니다.")
    @PostMapping(value = "/status")
    public ResponseDto<Void> updateOrderStatus(
         @RequestBody @Valid UpdateAdminOrdersStatusRequestDto requestDto
    ) {
        updateAdminOrdersStatusUseCase.execute(requestDto);
        return ResponseDto.ok(null);
    }
}
