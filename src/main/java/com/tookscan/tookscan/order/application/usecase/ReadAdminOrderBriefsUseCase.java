package com.tookscan.tookscan.order.application.usecase;

import com.tookscan.tookscan.core.annotation.bean.UseCase;
import com.tookscan.tookscan.order.application.dto.response.ReadAdminOrderBriefsResponseDto;

@UseCase
public interface ReadAdminOrderBriefsUseCase {

    ReadAdminOrderBriefsResponseDto execute();
}
