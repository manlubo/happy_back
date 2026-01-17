package com.gitbaby.happy_back.domain.member.exception;

import com.gitbaby.happy_back.domain.common.exception.HappyException;
import org.springframework.http.HttpStatus;

public class TelCodeMismatchException extends HappyException {
  public TelCodeMismatchException() {
    super("TEL_CODE_MISMATCH", "전화번호 인증코드가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
  }
}
