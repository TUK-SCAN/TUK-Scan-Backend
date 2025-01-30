package com.tookscan.tookscan.order.application.usecase;

import com.tookscan.tookscan.core.annotation.bean.UseCase;
import com.tookscan.tookscan.order.application.dto.request.UpdateAdminOrderDocumentsRequestDto;

@UseCase
public interface UpdateAdminOrderDocumentsUseCase {
    void execute(UpdateAdminOrderDocumentsRequestDto requestDto);
}
