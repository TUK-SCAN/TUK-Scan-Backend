package com.tookscan.tookscan.term.domain.service;

import com.tookscan.tookscan.term.domain.Term;
import com.tookscan.tookscan.term.domain.type.ETermType;
import org.springframework.stereotype.Service;

@Service
public class TermService {

    public Term createTerm(String type, String title, String content, Boolean isRequired) {
        return Term.builder()
                .type(ETermType.fromString(type))
                .title(title)
                .content(content)
                .isRequired(isRequired)
                .build();
    }
}
