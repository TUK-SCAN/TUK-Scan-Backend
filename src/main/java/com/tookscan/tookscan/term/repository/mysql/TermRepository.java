package com.tookscan.tookscan.term.repository.mysql;

import com.tookscan.tookscan.term.domain.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermRepository extends JpaRepository<Term, Long> {
}
