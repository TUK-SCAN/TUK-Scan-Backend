package com.tookscan.tookscan.order.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tookscan.tookscan.order.domain.type.ERecoveryOption;
import com.tookscan.tookscan.order.domain.type.EScanStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record UpdateAdminOrderDocumentsRequestDto(
        @JsonProperty("documents")
        @NotNull(message = "documents는 null일 수 없습니다.")
        @Valid
        List<DocumentDto> documents,

        @JsonProperty("delivery_price")
        @NotNull(message = "delivery_price는 null일 수 없습니다.")
        Integer deliveryPrice
) {
    public record DocumentDto(

            @JsonProperty("id")
            @NotNull(message = "id는 null일 수 없습니다.")
            Long id,

            @JsonProperty("name")
            @NotBlank(message = "name은 null일 수 없습니다.")
            String name,

            @JsonProperty("page_count")
            @NotNull(message = "page_count는 null일 수 없습니다.")
            Integer pageCount,

            @JsonProperty("recovery_option")
            @NotNull(message = "recovery_option은 null일 수 없습니다.")
            ERecoveryOption recoveryOption,

            @JsonProperty("additional_price")
            @NotNull(message = "additional_price는 null일 수 없습니다.")
            Integer additionalPrice,

            @JsonProperty("scan_status")
            @NotNull(message = "scan_status는 null일 수 없습니다.")
            EScanStatus scanStatus
    ) {
    }
}
