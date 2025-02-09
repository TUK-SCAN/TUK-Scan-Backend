package com.tookscan.tookscan.order.application.service;

import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import com.tookscan.tookscan.core.utility.ScannerUtil;
import com.tookscan.tookscan.order.application.usecase.CreateAdminDocumentsPdfUseCase;
import com.tookscan.tookscan.order.domain.Document;
import com.tookscan.tookscan.order.domain.type.EOrderStatus;
import com.tookscan.tookscan.order.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateAdminDocumentsPdfService implements CreateAdminDocumentsPdfUseCase {
    private final DocumentRepository documentRepository;

    private final ScannerUtil scannerUtil;

    @Override
    @Transactional
    public void execute(Long documentId) {
        Document document = documentRepository.findByIdOrElseThrow(documentId);

        EOrderStatus orderStatus = document.getOrder().getOrderStatus();
        if (orderStatus != EOrderStatus.SCAN_WAITING && orderStatus != EOrderStatus.SCAN_IN_PROGRESS) {
            throw new CommonException(ErrorCode.INVALID_ORDER_STATUS, "주문 상태: " + orderStatus.getDescription());
        }

        if (orderStatus == EOrderStatus.SCAN_WAITING) {
            document.getOrder().updateOrderStatus(EOrderStatus.SCAN_IN_PROGRESS);
        }
        
        String taskId = scannerUtil.startScan(document.getOrder().getId(), document.getId(), document.getName());
        document.updateScanTaskId(taskId);
    }
}