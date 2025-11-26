package com.gitbaby.happy_back.domain.member.exception;

import com.gitbaby.happy_back.domain.common.exception.HappyException;
import org.springframework.http.HttpStatus;

public class InvalidEmailTokenException extends HappyException {
  public InvalidEmailTokenException() {
    super("INVALID_EMAIL_TOKEN", "이메일 인증용 토큰이 필요합니다.", HttpStatus.BAD_REQUEST);
  }
}