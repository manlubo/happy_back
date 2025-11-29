package com.gitbaby.happy_back.domain.term.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gitbaby.happy_back.domain.term.entity.Term;

public interface TermRepository extends JpaRepository<Term, Long> {

}
