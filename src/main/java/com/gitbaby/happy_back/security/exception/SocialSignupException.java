package com.gitbaby.happy_back.security.exception;

import com.gitbaby.happy_back.security.recode.SocialUser;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

public class SocialSignupException extends AuthenticationException {
  @Getter
  private final SocialUser socialUser;

  public SocialSignupException(SocialUser socialUser) {
    super("PROVIDER_SIGNUP_REQUIRED");
    this.socialUser = socialUser;
  }
}
