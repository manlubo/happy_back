package com.gitbaby.happy_back.domain.member.service;

import com.gitbaby.happy_back.domain.member.dto.MemberSignupRequest;

public interface AuthService {
  public Long userSignup (MemberSignupRequest memberSignupRequest, String emailVerificationToken);
}
