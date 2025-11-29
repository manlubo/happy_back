package com.gitbaby.happy_back.domain.term.service;

import com.gitbaby.happy_back.domain.term.dto.TermCreateRequest;
import com.gitbaby.happy_back.domain.term.en.TermType;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Log4j2
class TermServiceImplTest {
  @Autowired
  private TermService termService;


  @Test
  @Transactional
  void createTerm() {
    TermCreateRequest termCreateRequest = new TermCreateRequest(TermType.PRIVACY, "개인정보처리방침", true);
    log.info(termService.createTerm(termCreateRequest));
  }
}