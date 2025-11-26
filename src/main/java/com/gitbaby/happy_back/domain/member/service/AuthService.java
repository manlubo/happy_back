package com.gitbaby.happy_back.domain.member.service;

import com.gitbaby.happy_back.domain.member.dto.*;

public interface AuthService {
  boolean signupEmailVerification(MemberSignupEmailRequest memberSignupEmailRequest);
  MemberReadSignupEmailResponse signupEmailVerified(MemberReadSignupEmailRequest memberReadSignupEmailRequest);
  MemberSignupResponse signup (MemberSignupRequest memberSignupRequest, String emailVerificationToken);
  MemberCookieWithLoginResponse login (MemberLoginRequest memberLoginRequest);
}
