package com.gitbaby.happy_back.domain.common.exception;

import org.springframework.http.HttpStatus;

public class SmsSendException extends HappyException {
  public SmsSendException() {
    super("SMS_SEND_ERROR", "인증 문자 전송에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
