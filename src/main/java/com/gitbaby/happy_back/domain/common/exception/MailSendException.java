package com.gitbaby.happy_back.domain.common.exception;

import org.springframework.http.HttpStatus;

public class MailSendException extends HappyException {
  public MailSendException(Throwable cause) {
    super("MAIL_SEND_ERROR", cause.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
