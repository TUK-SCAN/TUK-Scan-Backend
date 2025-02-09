package com.tookscan.tookscan.order.application.usecase;

import com.tookscan.tookscan.core.annotation.bean.UseCase;
import com.tookscan.tookscan.order.application.dto.response.ReadAdminDocumentsScanStatusResponseDto;

@UseCase
public interface ReadAdminDocumentsScanStatusUseCase {
    ReadAdminDocumentsScanStatusResponseDto execute(Long documentId);
}
