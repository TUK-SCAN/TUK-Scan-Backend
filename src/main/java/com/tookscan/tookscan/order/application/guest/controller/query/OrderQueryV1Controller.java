package com.tookscan.tookscan.order.application.guest.controller.query;

import com.tookscan.tookscan.core.dto.ResponseDto;
import com.tookscan.tookscan.order.application.guest.dto.response.ReadOrderDetailResponseDto;
import com.tookscan.tookscan.order.application.guest.usecase.ReadOrderDetailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/guests/orders")
public class OrderQueryV1Controller {

    private final ReadOrderDetailUseCase readOrderDetailUseCase;

    /**
     * 4.12 비회원 주문 상세 조회
     */
    @GetMapping(value = "/details")
    public ResponseDto<ReadOrderDetailResponseDto> getGuestOrderDetail(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "order-number") Long orderNumber
    ) {
        return ResponseDto.ok(readOrderDetailUseCase.execute(name, orderNumber));
    }
}
