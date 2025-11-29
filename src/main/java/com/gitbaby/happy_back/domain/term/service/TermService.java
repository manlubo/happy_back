package com.gitbaby.happy_back.domain.term.service;

import com.gitbaby.happy_back.domain.term.dto.TermCreateRequest;

public interface TermService {
  String createTerm(TermCreateRequest termCreateRequest);
}
