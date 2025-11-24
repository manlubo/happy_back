package com.gitbaby.happy_back.domain.member.service;

import com.gitbaby.happy_back.domain.member.dto.MemberSignupRequest;

public interface MemberService {
  boolean hasEmail(String email);
  Long userSignUp(MemberSignupRequest memberSignupRequest);
  Long OrgSignUp();
}
