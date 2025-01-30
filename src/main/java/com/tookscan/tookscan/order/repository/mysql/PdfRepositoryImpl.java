package com.tookscan.tookscan.order.repository.mysql;

import com.tookscan.tookscan.order.repository.PdfRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PdfRepositoryImpl implements PdfRepository {
    private final PdfJpaRepository pdfJpaRepository;
}
