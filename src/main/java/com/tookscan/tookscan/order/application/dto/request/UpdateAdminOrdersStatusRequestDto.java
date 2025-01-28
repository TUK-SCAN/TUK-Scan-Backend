package com.tookscan.tookscan.order.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tookscan.tookscan.order.domain.type.EOrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Valid
public record UpdateAdminOrdersStatusRequestDto (
        @JsonProperty("order_ids")
        @NotNull(message = "order_ids는 null일 수 없습니다.")
        List<Long> orderIds,

        @JsonProperty("status")
        @NotNull(message = "status는 null일 수 없습니다.")
        EOrderStatus status
){ }
