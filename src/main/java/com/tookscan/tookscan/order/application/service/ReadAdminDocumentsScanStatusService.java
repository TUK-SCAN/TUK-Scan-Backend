package com.tookscan.tookscan.order.application.service;

import com.tookscan.tookscan.core.utility.ScannerUtil;
import com.tookscan.tookscan.order.application.dto.response.ReadAdminDocumentsScanStatusResponseDto;
import com.tookscan.tookscan.order.application.usecase.ReadAdminDocumentsScanStatusUseCase;
import com.tookscan.tookscan.order.domain.Document;
import com.tookscan.tookscan.order.domain.type.EScanStatus;
import com.tookscan.tookscan.order.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReadAdminDocumentsScanStatusService implements ReadAdminDocumentsScanStatusUseCase {

    private final DocumentRepository documentRepository;

    private final ScannerUtil scannerUtil;

    @Override
    @Transactional(readOnly = true)
    public ReadAdminDocumentsScanStatusResponseDto execute(Long documentId) {
        Document document = documentRepository.findByIdOrElseThrow(documentId);
        EScanStatus status = scannerUtil.getScanStatus(document.getScanTaskId());
        return ReadAdminDocumentsScanStatusResponseDto.of(status);
    }
}
