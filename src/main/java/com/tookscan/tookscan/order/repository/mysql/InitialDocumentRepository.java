package com.tookscan.tookscan.order.repository.mysql;

import com.tookscan.tookscan.order.domain.InitialDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InitialDocumentRepository extends JpaRepository<InitialDocument, Long> {
}
