package com.gitbaby.happy_back.domain.member.exception;

import com.gitbaby.happy_back.domain.common.exception.HappyException;

public class EmailMismatchException extends HappyException {
  public EmailMismatchException() {
    super("EMAIL_MISMATCH", "이메일 정보가 일치하지 않습니다.");
  }
}
