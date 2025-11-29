package com.gitbaby.happy_back.domain.term.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gitbaby.happy_back.domain.common.util.VersionUtil;
import com.gitbaby.happy_back.domain.term.dto.TermCreateRequest;
import com.gitbaby.happy_back.domain.term.entity.Term;
import com.gitbaby.happy_back.domain.term.mapper.TermMapper;
import com.gitbaby.happy_back.domain.term.repository.TermRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class TermServiceImpl implements TermService {
  private final TermRepository termRepository;
  private final TermMapper termMapper;
  private final VersionUtil versionUtil;

  @Override
  public String createTerm(TermCreateRequest termCreateRequest) {
    List<Term> terms = termRepository.findByType(termCreateRequest.getType());

    String version = "1.0.0";
    
    if (!terms.isEmpty()) {
      String latest = terms.stream()
        .map(Term::getVersion)
        .max(versionUtil::compare)
        .get();
      
        version = versionUtil.nextMajor(latest);
    }
    
    Term term = termMapper.toEntity(termCreateRequest);
    term.setVersion(version);
    
    return termRepository.save(term).getVersion();
  }

  
}
