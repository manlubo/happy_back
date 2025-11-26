package com.gitbaby.happy_back.domain.member.service;

import com.gitbaby.happy_back.domain.member.dto.*;

public interface AuthService {
  public boolean signupEmailVerification(MemberSignupEmailRequest memberSignupEmailRequest);
  public MemberReadSignupEmailResponse signupEmailVerified(MemberReadSignupEmailRequest memberReadSignupEmailRequest);
  public MemberSignupResponse signup (MemberSignupRequest memberSignupRequest, String emailVerificationToken);
}
