package com.gitbaby.happy_back.domain.term.service;

import com.gitbaby.happy_back.domain.term.dto.TermCreateRequest;
import com.gitbaby.happy_back.domain.term.dto.TermCreateResponse;

public interface TermService {
  TermCreateResponse createTerm(TermCreateRequest termCreateRequest);
}
