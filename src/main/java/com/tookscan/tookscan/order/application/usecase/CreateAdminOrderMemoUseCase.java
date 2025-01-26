package com.tookscan.tookscan.order.application.usecase;

import com.tookscan.tookscan.core.annotation.bean.UseCase;
import com.tookscan.tookscan.order.application.dto.request.CreateAdminOrderMemoRequestDto;

@UseCase
public interface CreateAdminOrderMemoUseCase {
    void execute(Long orderId, CreateAdminOrderMemoRequestDto requestDto);
}
