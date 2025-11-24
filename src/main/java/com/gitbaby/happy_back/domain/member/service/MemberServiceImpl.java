package com.gitbaby.happy_back.domain.member.service;

import com.gitbaby.happy_back.domain.member.dto.MemberSignupRequest;
import com.gitbaby.happy_back.domain.member.en.MemberStatus;
import com.gitbaby.happy_back.domain.member.entity.Member;
import com.gitbaby.happy_back.domain.member.mapper.MemberMapper;
import com.gitbaby.happy_back.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
  private final MemberRepository memberRepository;
  private final MemberMapper memberMapper;

  @Override
  public boolean hasEmail(String email) {
    return memberRepository.existsByEmail(email);
  }

  // 회원가입 - 일반회원
  @Override
  public Long userSignUp(MemberSignupRequest memberSignupRequest) {

    Member member = memberMapper.toEntity(memberSignupRequest);
    member.setStatus(MemberStatus.ACTIVE);
    memberRepository.save(member);

    return member.getId();
  }

  // 회원가입 - 기관회원
  @Override
  public Long OrgSignUp() {
    return 0L;
  }
}
