package com.tookscan.tookscan.order.application.service;

import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import com.tookscan.tookscan.order.application.dto.request.UpdateAdminOrderDocumentsRequestDto;
import com.tookscan.tookscan.order.application.usecase.UpdateAdminOrderDocumentsUseCase;
import com.tookscan.tookscan.order.domain.Document;
import com.tookscan.tookscan.order.domain.service.DocumentService;
import com.tookscan.tookscan.order.repository.DocumentRepository;
import com.tookscan.tookscan.order.repository.OrderRepository;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateAdminOrderDocumentsService implements UpdateAdminOrderDocumentsUseCase {

    private final OrderRepository orderRepository;
    private final DocumentRepository documentRepository;

    private final DocumentService documentService;

    @Override
    @Transactional
    public void execute(Long orderId, UpdateAdminOrderDocumentsRequestDto requestDto) {
        Set<Long> orderDocumentIds = orderRepository.findByIdOrElseThrow(orderId).getDocuments()
                .stream().map(Document::getId).collect(Collectors.toSet());

        List<Long> documentIds = requestDto.documents().stream()
                .map(UpdateAdminOrderDocumentsRequestDto.DocumentDto::id)
                .toList();

        List<Long> invalidDocumentIds = documentIds.stream()
                .filter(id -> !orderDocumentIds.contains(id))
                .toList();

        if (!invalidDocumentIds.isEmpty()) {
            throw new CommonException(ErrorCode.INVALID_ARGUMENT,
                    "해당 주문(Order)에 속하지 않는 문서(Document)가 포함되었습니다: " + invalidDocumentIds);
        }

        Map<Long, Document> documentMap = documentRepository.findAllByIdsOrElseThrow(documentIds)
                .stream()
                .collect(Collectors.toMap(Document::getId, document -> document));

        requestDto.documents().forEach(document -> {
            documentService.updateDocument(
                    documentMap.get(document.id()),
                    document.name(),
                    document.pageCount(),
                    document.recoveryOption(),
                    document.additionalPrice(),
                    document.scanStatus()
            );
        });
    }
}
