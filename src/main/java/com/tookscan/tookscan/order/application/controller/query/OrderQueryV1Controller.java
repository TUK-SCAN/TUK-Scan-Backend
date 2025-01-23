package com.tookscan.tookscan.order.application.controller.query;

import com.tookscan.tookscan.core.annotation.security.AccountID;
import com.tookscan.tookscan.core.dto.ResponseDto;
import com.tookscan.tookscan.order.application.dto.response.ReadGuestOrderDetailResponseDto;
import com.tookscan.tookscan.order.application.dto.response.ReadOrderOverviewResponseDto;
import com.tookscan.tookscan.order.application.dto.response.ReadUserOrderDetailResponseDto;
import com.tookscan.tookscan.order.application.usecase.ReadGuestOrderDetailUseCase;
import com.tookscan.tookscan.order.application.usecase.ReadOrderOverviewUseCase;
import com.tookscan.tookscan.order.application.usecase.ReadUserOrderDetailUseCase;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class OrderQueryV1Controller {
    private final ReadOrderOverviewUseCase readOrderOverviewUseCase;
    private final ReadUserOrderDetailUseCase readUserOrderDetailUseCase;
    private final ReadGuestOrderDetailUseCase readGuestOrderDetailUseCase;

    /**
     * 4.10 회원 주문 내역 조회
     */
    @GetMapping(value = "/users/orders/overviews")
    public ResponseDto<ReadOrderOverviewResponseDto> getOrderOverview(
            @Parameter(hidden = true) @AccountID UUID accountId,
            @Min(0) @RequestParam(value = "page", defaultValue = "0") int page,
            @Min(0) @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "direction", required = false, defaultValue = "desc") String direction

    ) {
        return ResponseDto.ok(readOrderOverviewUseCase.execute(accountId, page, size, sort, search, direction));
    }

    /**
     * 4.11 회원 주문 상세 조회
     */
    @GetMapping(value = "/users/orders/{orderId}/details")
    public ResponseDto<ReadUserOrderDetailResponseDto> getUserOrderDetail(
            @Parameter(hidden = true) @AccountID UUID accountId,
            @PathVariable Long orderId
    ) {
        return ResponseDto.ok(readUserOrderDetailUseCase.execute(accountId, orderId));
    }

    /**
     * 4.12 비회원 주문 상세 조회
     */
    @GetMapping(value = "/guests/orders/details")
    public ResponseDto<ReadGuestOrderDetailResponseDto> getGuestOrderDetail(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "order-number") Long orderNumber
    ) {
        return ResponseDto.ok(readGuestOrderDetailUseCase.execute(name, orderNumber));
    }

}
