package com.tookscan.tookscan.account.application.usecase;

import com.tookscan.tookscan.account.application.dto.response.ReadAccountBriefResponseDto;
import com.tookscan.tookscan.core.annotation.bean.UseCase;

import java.util.UUID;

@UseCase
public interface ReadAccountBriefUseCase {
    ReadAccountBriefResponseDto execute(UUID accountId);
}
