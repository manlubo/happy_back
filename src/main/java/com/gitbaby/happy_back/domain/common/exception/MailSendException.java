package com.gitbaby.happy_back.domain.common.exception;

public class MailSendException extends HappyException {
  public MailSendException(Throwable cause) {
    super("MAIL_SEND_ERROR", cause.getMessage());
  }
}
