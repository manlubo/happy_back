package com.gitbaby.happy_back.domain.member.exception;

import com.gitbaby.happy_back.domain.common.exception.HappyException;

public class EmailAlreadyExistsException extends HappyException {
  public EmailAlreadyExistsException() {
    super("EMAIL_ALREADY_EXISTS", "이미 사용 중인 이메일입니다.");
  }
}
