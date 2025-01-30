package com.tookscan.tookscan.term.application.service;

import com.tookscan.tookscan.term.application.dto.request.UpdateAdminTermRequestDto;
import com.tookscan.tookscan.term.application.usecase.UpdateAdminTermUseCase;
import com.tookscan.tookscan.term.domain.Term;
import com.tookscan.tookscan.term.domain.service.TermService;
import com.tookscan.tookscan.term.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateAdminTermService implements UpdateAdminTermUseCase {

    private final TermRepository termRepository;

    private final TermService termService;

    @Override
    @Transactional
    public void execute(UpdateAdminTermRequestDto requestDto) {

        requestDto.terms().forEach(dto -> {
            Term term = termRepository.findByIdOrElseThrow(dto.id());
            termService.updateTerm(
                    term,
                    dto.title(),
                    dto.content(),
                    dto.isRequired(),
                    dto.isVisible()
            );
            termRepository.save(term);
        });
    }
}
