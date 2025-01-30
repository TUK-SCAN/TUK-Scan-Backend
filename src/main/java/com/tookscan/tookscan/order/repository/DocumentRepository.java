package com.tookscan.tookscan.order.repository;

import com.tookscan.tookscan.order.domain.Document;

public interface DocumentRepository {
    void save(Document document);

    Document findByIdOrElseThrow(Long id);
}
