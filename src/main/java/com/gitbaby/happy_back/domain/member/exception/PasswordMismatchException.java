package com.gitbaby.happy_back.domain.member.exception;

import com.gitbaby.happy_back.domain.common.exception.HappyException;
import org.springframework.http.HttpStatus;

public class PasswordMismatchException extends HappyException {
  public PasswordMismatchException() {
    super("PASSWORD_MISMATCH", "비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
  }
}
