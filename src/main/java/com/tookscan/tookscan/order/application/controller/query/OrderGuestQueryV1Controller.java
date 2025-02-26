package com.tookscan.tookscan.order.application.controller.query;

import com.tookscan.tookscan.core.dto.ResponseDto;
import com.tookscan.tookscan.order.application.dto.response.ReadGuestOrderDetailResponseDto;
import com.tookscan.tookscan.order.application.dto.response.ReadGuestOrderSummaryResponseDto;
import com.tookscan.tookscan.order.application.usecase.ReadGuestOrderDetailUseCase;
import com.tookscan.tookscan.order.application.usecase.ReadGuestOrderSummaryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order", description = "Order 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/guests/orders")
public class OrderGuestQueryV1Controller {

    private final ReadGuestOrderDetailUseCase readGuestOrderDetailUseCase;
    private final ReadGuestOrderSummaryUseCase readGuestOrderSummaryUseCase;

    /**
     * 4.2.1 비회원 주문 상세 조회
     */
    @Operation(summary = "비회원 주문 상세 조회", description = "비회원이 주문 상세를 조회합니다.")
    @GetMapping(value = "/details")
    public ResponseDto<ReadGuestOrderDetailResponseDto> getGuestOrderDetail(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "order-number") String orderNumber
    ) {
        return ResponseDto.ok(readGuestOrderDetailUseCase.execute(name, orderNumber));
    }

    /**
     * 4.2.17 비회원 주문 요약 조회
     */
    @Operation(summary = "비회원 주문 요약 조회", description = "비회원이 주문 요약을 조회합니다.")
    @GetMapping(value = "/summary")
    public ResponseDto<ReadGuestOrderSummaryResponseDto> getUserOrderSummary(
            @RequestParam(value = "order-number") String orderNumber
    ) {
        return ResponseDto.ok(readGuestOrderSummaryUseCase.execute(orderNumber));
    }
}
