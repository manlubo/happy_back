package com.gitbaby.happy_back.domain.member.exception;

import com.gitbaby.happy_back.domain.common.exception.HappyException;

public class EmailVerificationExpiredException extends HappyException {
  public EmailVerificationExpiredException() {
    super("EMAIL_VERIFICATION_EXPIRED", "이메일 인증 정보가 만료되었습니다.");
  }
}
