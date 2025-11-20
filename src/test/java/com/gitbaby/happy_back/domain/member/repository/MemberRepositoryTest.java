package com.gitbaby.happy_back.domain.member.repository;

import com.gitbaby.happy_back.domain.member.en.MemberStatus;
import com.gitbaby.happy_back.domain.member.entity.Member;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Log4j2
class MemberRepositoryTest {
  @Autowired
  private MemberRepository memberRepository;

  @Test
  void memberCrud() {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    Member member = memberRepository.save(Member.builder()
        .email("manlubo11@gmail.com")
        .password(passwordEncoder.encode("12345678"))
        .name("전상현")
        .status(MemberStatus.ACTIVE)
        .tel("010-6687-8628")
        .address("서울특별시 독산동")
      .build());

    log.info(member);

    member.setAddress("서울특별시 독산3동");
    memberRepository.save(member);
    log.info(memberRepository.findByEmail("manlubo11@gmail.com"));

    memberRepository.delete(member);
    log.info(memberRepository.findByEmail("manlubo11@gmail.com").isEmpty());
  }
}