package com.tookscan.tookscan.term.repository.mysql;

import com.tookscan.tookscan.term.domain.Term;
import com.tookscan.tookscan.term.domain.type.ETermType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TermJpaRepository extends JpaRepository<Term, Long> {

    List<Term> findAllByType(ETermType type);

}
