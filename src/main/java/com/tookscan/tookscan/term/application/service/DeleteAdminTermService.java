package com.tookscan.tookscan.term.application.service;

import com.tookscan.tookscan.term.application.usecase.DeleteAdminTermUseCase;
import com.tookscan.tookscan.term.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteAdminTermService implements DeleteAdminTermUseCase {

    private final TermRepository termRepository;

    @Override
    @Transactional
    public void execute(Long termId) {
        termRepository.deleteById(termId);
    }
}
