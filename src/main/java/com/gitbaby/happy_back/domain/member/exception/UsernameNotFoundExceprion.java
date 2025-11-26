package com.gitbaby.happy_back.domain.member.exception;

import com.gitbaby.happy_back.domain.common.exception.HappyException;
import org.springframework.http.HttpStatus;

public class UsernameNotFoundExceprion extends HappyException {
  public UsernameNotFoundExceprion() {
    super("USERNAME_NOT_FOUND", "해당 정보로 가입된 유저가 없습니다.", HttpStatus.BAD_REQUEST);
  }
}
