package com.tookscan.tookscan.term.repository.mysql;

import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import com.tookscan.tookscan.term.domain.Term;
import com.tookscan.tookscan.term.domain.type.ETermType;
import com.tookscan.tookscan.term.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TermRepositoryImpl implements TermRepository {

    private final TermJpaRepository termJpaRepository;

    @Override
    public void save(Term term) {
        termJpaRepository.save(term);
    }

    @Override
    public Term findByIdOrElseThrow(Long id) {
        return termJpaRepository.findById(id)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));
    }

    @Override
    public List<Term> findAllByType(ETermType type) {
        return termJpaRepository.findAllByType(type);
    }

    @Override
    public void deleteById(Long id) {
        termJpaRepository.deleteById(id);
    }
}
