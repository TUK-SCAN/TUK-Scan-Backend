package com.tookscan.tookscan.order.repository.mysql;

import com.tookscan.tookscan.order.domain.InitialDocument;
import com.tookscan.tookscan.order.repository.InitialDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InitialDocumentRepositoryImpl implements InitialDocumentRepository {
    private final InitialDocumentJpaRepository initialDocumentJpaRepository;

    @Override
    public void save(InitialDocument initialDocument) {
        initialDocumentJpaRepository.save(initialDocument);
    }
}
