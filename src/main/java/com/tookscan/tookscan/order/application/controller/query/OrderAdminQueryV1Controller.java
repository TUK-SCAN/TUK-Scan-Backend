package com.tookscan.tookscan.order.application.controller.query;

import com.tookscan.tookscan.core.dto.ResponseDto;
import com.tookscan.tookscan.order.application.dto.response.ReadAdminOrderBriefResponseDto;
import com.tookscan.tookscan.order.application.usecase.ReadAdminOrderBriefUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order", description = "Order 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admins")
public class OrderAdminQueryV1Controller {

    private final ReadAdminOrderBriefUseCase readAdminOrderBriefUseCase;

    @GetMapping("/orders/briefs")
    public ResponseDto<ReadAdminOrderBriefResponseDto> readOrderBriefs() {
        return ResponseDto.ok(readAdminOrderBriefUseCase.execute());
    }
}
