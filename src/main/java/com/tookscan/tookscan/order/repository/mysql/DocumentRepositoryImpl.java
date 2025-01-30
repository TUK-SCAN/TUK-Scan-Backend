package com.tookscan.tookscan.order.repository.mysql;

import com.tookscan.tookscan.order.domain.Document;
import com.tookscan.tookscan.order.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DocumentRepositoryImpl implements DocumentRepository {

    private final DocumentJpaRepository documentJpaRepository;

    @Override
    public void save(Document document) {
        documentJpaRepository.save(document);
    }
}
