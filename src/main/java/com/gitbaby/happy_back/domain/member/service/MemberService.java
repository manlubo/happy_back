package com.gitbaby.happy_back.domain.member.service;

import com.gitbaby.happy_back.domain.member.dto.MemberSignupRequest;
import com.gitbaby.happy_back.domain.member.dto.MemberSignupResponse;

public interface MemberService {
  boolean hasEmail(String email);
  MemberSignupResponse signup(MemberSignupRequest memberSignupRequest);
}
