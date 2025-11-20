package com.gitbaby.happy_back.security.exception;

import com.gitbaby.happy_back.security.recode.SocialUser;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

public class SocialLinkException extends AuthenticationException {
  @Getter
  private final SocialUser socialUser;

  public SocialLinkException(SocialUser socialUser) {
    super("PROVIDER_LINK_REQUIRED");
    this.socialUser = socialUser;
  }
}
