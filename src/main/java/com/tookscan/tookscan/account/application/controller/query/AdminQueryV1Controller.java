package com.tookscan.tookscan.account.application.controller.query;

import com.tookscan.tookscan.account.application.dto.response.ReadAdminUserOverviewResponseDto;
import com.tookscan.tookscan.account.application.usecase.ReadAdminUserOverviewUseCase;
import com.tookscan.tookscan.core.dto.ResponseDto;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admins")
public class AdminQueryV1Controller {

    private final ReadAdminUserOverviewUseCase readAdminUserOverviewUseCase;

    /**
     * 3.6 (관리자) 유저 리스트 조회
     */
    @GetMapping("/users/overviews")
    public ResponseDto<ReadAdminUserOverviewResponseDto> readAdminUserOverview(
            @RequestParam(value = "search-type", required = false) String searchType,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "group-id", required = false) Long groupId,
            @RequestParam(value = "start-date", required = false) String startDate,
            @RequestParam(value = "end-date", required = false) String endDate,
            @RequestParam(value = "page", defaultValue = "1") @Min(value = 1, message = "페이지는 1 이상이어야 합니다") Integer page,
            @RequestParam(value = "size", defaultValue = "10") @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다") Integer size
    ) {
        return ResponseDto.ok(readAdminUserOverviewUseCase.execute(
                searchType,
                search,
                groupId,
                startDate,
                endDate,
                page,
                size
        ));
    }
}
