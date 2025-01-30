package com.tookscan.tookscan.order.application.service;

import com.tookscan.tookscan.order.application.dto.request.UpdateAdminOrderDocumentsRequestDto;
import com.tookscan.tookscan.order.application.usecase.UpdateAdminOrderDocumentsUseCase;
import com.tookscan.tookscan.order.domain.service.DocumentService;
import com.tookscan.tookscan.order.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateAdminOrderDocumentsService implements UpdateAdminOrderDocumentsUseCase {

    private final DocumentRepository documentRepository;
    private final DocumentService documentService;

    @Override
    @Transactional
    public void execute(UpdateAdminOrderDocumentsRequestDto requestDto) {
        requestDto.documents().forEach(document -> {
            documentService.updateDocument(
                    documentRepository.findByIdOrElseThrow(document.id()),
                    document.name(),
                    document.pageCount(),
                    document.recoveryOption(),
                    document.additionalPrice(),
                    document.scanStatus()
            );
        });
    }
}
