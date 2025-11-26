package com.gitbaby.happy_back.domain.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HappyException extends RuntimeException {
  private final String errorCode;
  private final HttpStatus status;

  public HappyException(String errorCode, String message, HttpStatus status)
  {
    super(message);
    this.errorCode = errorCode;
    this.status = status;
  }
}
