package com.tookscan.tookscan.order.application.controller.query;

import com.tookscan.tookscan.core.dto.ResponseDto;
import com.tookscan.tookscan.order.application.dto.response.ReadAdminOrderBriefsResponseDto;
import com.tookscan.tookscan.order.application.dto.response.ReadAdminOrderSummariesResponseDto;
import com.tookscan.tookscan.order.application.usecase.ReadAdminOrderBriefsUseCase;
import com.tookscan.tookscan.order.application.usecase.ReadAdminOrderSummariesUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order", description = "Order 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admins")
public class OrderAdminQueryV1Controller {

    private final ReadAdminOrderBriefsUseCase readAdminOrderBriefsUseCase;
    private final ReadAdminOrderSummariesUseCase readAdminOrderSummariesUseCase;

    /**
     * 4.2.5 관리자 주문 요약 정보 조회
     */
    @Operation(summary = "관리자 주문 요약 정보 조회", description = "관리자가 주문 요약 정보를 조회합니다.")
    @GetMapping("/orders/briefs")
    public ResponseDto<ReadAdminOrderBriefsResponseDto> readOrderBriefs() {
        return ResponseDto.ok(readAdminOrderBriefsUseCase.execute());
    }

    /**
     * 4.2.6 관리자 주문 리스트 요약 정보 조회
     */
    @Operation(summary = "관리자 주문 리스트 요약 정보 조회", description = "관리자가 주문 리스트 요약 정보를 조회합니다.")
    @GetMapping("/orders/summaries")
    public ResponseDto<ReadAdminOrderSummariesResponseDto> readOrderSummaries(
            @RequestParam(value = "page", defaultValue = "1") @Min(value = 1, message = "페이지는 1 이상이어야 합니다") Integer page,
            @RequestParam(value = "size", defaultValue = "10") @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다") Integer size,
            @RequestParam(value = "start-date", required = false) String startDate,
            @RequestParam(value = "end-date", required = false) String endDate,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "search-type", required = false) String searchType,
            @RequestParam(value = "sort", defaultValue = "created-at") String sort,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) {
        return ResponseDto.ok(readAdminOrderSummariesUseCase.execute(page, size, startDate, endDate, search, searchType, sort, direction));
    }
}
