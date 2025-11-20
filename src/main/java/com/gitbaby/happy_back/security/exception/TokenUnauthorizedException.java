package com.gitbaby.happy_back.security.exception;

import com.gitbaby.happy_back.domain.common.exception.HappyException;

public class TokenUnauthorizedException extends HappyException {
  public TokenUnauthorizedException() {
    super("TOKEN_UNAUTHORIZED", "유효하지 않은 토큰입니다.");
  }
}
