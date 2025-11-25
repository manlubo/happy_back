package com.gitbaby.happy_back.domain.member.service;

import com.gitbaby.happy_back.domain.member.dto.MemberReadSignupEmailRequest;
import com.gitbaby.happy_back.domain.member.dto.MemberSignupEmailRequest;
import com.gitbaby.happy_back.domain.member.dto.MemberReadSignupEmailResponse;
import com.gitbaby.happy_back.domain.member.dto.MemberSignupRequest;

public interface AuthService {
  public boolean signupEmailVerification(MemberSignupEmailRequest memberSignupEmailRequest);
  public MemberReadSignupEmailResponse signupEmailVerified(MemberReadSignupEmailRequest memberReadSignupEmailRequest);
  public Long signup (MemberSignupRequest memberSignupRequest, String emailVerificationToken);
}
