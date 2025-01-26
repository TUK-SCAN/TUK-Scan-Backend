package com.tookscan.tookscan.order.application.user.usecase;

import com.tookscan.tookscan.core.annotation.bean.UseCase;
import com.tookscan.tookscan.order.application.user.dto.request.CreateAdminOrderMemoRequestDto;

@UseCase
public interface CreateAdminOrderMemoUseCase {
    void execute(Long orderId, CreateAdminOrderMemoRequestDto requestDto);
}
