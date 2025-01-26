package com.tookscan.tookscan.order.application.user.controller.query;

import com.tookscan.tookscan.core.dto.ResponseDto;
import com.tookscan.tookscan.order.application.user.dto.response.ReadGuestOrderDetailResponseDto;
import com.tookscan.tookscan.order.application.user.usecase.ReadGuestOrderDetailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/guests/orders")
public class OrderGuestQueryV1Controller {

    private final ReadGuestOrderDetailUseCase readGuestOrderDetailUseCase;

    /**
     * 4.12 비회원 주문 상세 조회
     */
    @GetMapping(value = "/details")
    public ResponseDto<ReadGuestOrderDetailResponseDto> getGuestOrderDetail(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "order-number") Long orderNumber
    ) {
        return ResponseDto.ok(readGuestOrderDetailUseCase.execute(name, orderNumber));
    }
}
