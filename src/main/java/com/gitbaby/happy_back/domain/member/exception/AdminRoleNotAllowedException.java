package com.gitbaby.happy_back.domain.member.exception;

import com.gitbaby.happy_back.domain.common.exception.HappyException;

public class AdminRoleNotAllowedException extends HappyException {
  public AdminRoleNotAllowedException() {
    super("ADMIN_NOT_ALLOWED", "어드민에 접근할 권한이 없습니다.");
  }
}
