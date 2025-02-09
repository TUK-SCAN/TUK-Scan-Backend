package com.tookscan.tookscan.order.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ExportAdminDeliveriesRequestDto(
        @JsonProperty("start_date")
        @NotBlank
        @Pattern(regexp = "\\d{4}\\.\\d{2}\\.\\d{2}")
        String startDate,

        @JsonProperty("end_date")
        @NotBlank
        @Pattern(regexp = "\\d{4}\\.\\d{2}\\.\\d{2}")
        String endDate
) {
}