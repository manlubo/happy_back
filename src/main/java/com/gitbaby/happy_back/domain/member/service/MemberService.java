package com.gitbaby.happy_back.domain.member.service;

import com.gitbaby.happy_back.domain.member.dto.MemberSignupRequest;
import com.gitbaby.happy_back.domain.member.dto.MemberSignupResponse;
import com.gitbaby.happy_back.domain.member.entity.Member;

public interface MemberService {
  boolean hasEmail(String email);
  MemberSignupResponse signup(MemberSignupRequest memberSignupRequest);
  Member getMemberByUsername(String username);
}
