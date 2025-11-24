package com.gitbaby.happy_back.domain.member.service;

import com.gitbaby.happy_back.domain.member.dto.MemberSignupRequest;
import com.gitbaby.happy_back.domain.member.en.MemberStatus;
import com.gitbaby.happy_back.domain.member.en.Role;
import com.gitbaby.happy_back.domain.member.entity.Member;
import com.gitbaby.happy_back.domain.member.exception.AdminRoleNotAllowedException;
import com.gitbaby.happy_back.domain.member.mapper.MemberMapper;
import com.gitbaby.happy_back.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {
  private final MemberRepository memberRepository;
  private final MemberMapper memberMapper;

  @Override
  public boolean hasEmail(String email) {
    return memberRepository.existsByEmail(email);
  }

  // 회원가입
  @Override
  public Long signUp(MemberSignupRequest memberSignupRequest) {

    // 역할이 어드민일 때
    if(Role.ADMIN.equals(memberSignupRequest.getRole())){

    }

    Member member = memberMapper.toEntity(memberSignupRequest);
    switch (memberSignupRequest.getRole()){
      case ADMIN -> throw new AdminRoleNotAllowedException();


      case ORG -> {
        member.setStatus(MemberStatus.READY);

      }
      case USER -> {
        member.setStatus(MemberStatus.ACTIVE);

      }
    }

    log.info(memberRepository.save(member));

    return member.getId();
  }

}
