package com.tookscan.tookscan.order.repository.mysql;

import com.tookscan.tookscan.order.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentJpaRepository extends JpaRepository<Document, Long> {
}
