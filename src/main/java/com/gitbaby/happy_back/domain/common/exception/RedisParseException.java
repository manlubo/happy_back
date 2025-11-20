package com.gitbaby.happy_back.domain.common.exception;

public class RedisParseException extends HappyException {
  public RedisParseException() {
    super("REDIS_PARSE_ERROR", "Redis 데이터 파싱에 실패했습니다.");
  }
}
