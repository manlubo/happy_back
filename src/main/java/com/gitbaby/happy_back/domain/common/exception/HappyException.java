package com.gitbaby.happy_back.domain.common.exception;

import lombok.Getter;

@Getter
public class HappyException extends RuntimeException {
  private final String errorCode;

  public HappyException(String errorCode, String message)
  {
    super(message);
    this.errorCode = errorCode;
  }
}
