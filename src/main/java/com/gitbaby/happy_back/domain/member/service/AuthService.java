package com.gitbaby.happy_back.domain.member.service;

import com.gitbaby.happy_back.domain.member.dto.MemberSignupEmailRequest;
import com.gitbaby.happy_back.domain.member.dto.MemberSignupRequest;

public interface AuthService {
  public boolean signUpEmailVerification(MemberSignupEmailRequest memberSignupEmailRequest);
  public Long signup (MemberSignupRequest memberSignupRequest, String emailVerificationToken);
}
