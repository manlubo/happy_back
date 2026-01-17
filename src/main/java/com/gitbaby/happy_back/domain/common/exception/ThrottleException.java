package com.gitbaby.happy_back.domain.common.exception;

import org.springframework.http.HttpStatus;

public class ThrottleException extends HappyException {
  public ThrottleException() {
    super("THROTTLE_ERROR", "잠시후 다시 시도해 주세요.", HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
