package com.tookscan.tookscan.order.repository.mysql;

import com.tookscan.tookscan.order.domain.Pdf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfRepository extends JpaRepository<Pdf, Long> {
}
