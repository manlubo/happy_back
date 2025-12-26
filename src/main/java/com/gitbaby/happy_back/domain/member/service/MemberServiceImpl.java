package com.gitbaby.happy_back.domain.member.service;

import com.gitbaby.happy_back.domain.member.dto.MemberSignupRequest;
import com.gitbaby.happy_back.domain.member.dto.MemberSignupResponse;
import com.gitbaby.happy_back.domain.member.en.MemberStatus;
import com.gitbaby.happy_back.domain.member.entity.Member;
import com.gitbaby.happy_back.domain.member.exception.UsernameNotFoundExceprion;
import com.gitbaby.happy_back.domain.member.mapper.MemberMapper;
import com.gitbaby.happy_back.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import io.opentelemetry.instrumentation.annotations.WithSpan;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {
  private final MemberRepository memberRepository;
  private final MemberMapper memberMapper;

  // 이메일 가입 여부 체크
  @Override
  @WithSpan
  public boolean hasEmail(String email) {
    return memberRepository.existsByEmail(email);
  }

  // 회원가입
  @Override
  @WithSpan
  public MemberSignupResponse signup(MemberSignupRequest memberSignupRequest) {
    Member member = memberMapper.toEntity(memberSignupRequest);

    switch (memberSignupRequest.getRole()) {
      case ORG -> {
        member.setStatus(MemberStatus.READY);

      }
      case USER -> {
        member.setStatus(MemberStatus.ACTIVE);

      }
    }

    memberRepository.save(member);

    return new MemberSignupResponse(member.getId(), member.getRoles());
  }

  // 이메일인지 확인
  private boolean isEmail(String username) {
    return username.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
  }

  @Override
  @WithSpan
  public Member getMemberByUsername(String username) {
    Member member;
    if (isEmail(username)) {
      member = memberRepository.findByEmail(username).orElseThrow(UsernameNotFoundExceprion::new);
    } else {
      member = memberRepository.findByTel(username).orElseThrow(UsernameNotFoundExceprion::new);
    }

    return member;
  }

}
