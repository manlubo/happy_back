package com.gitbaby.happy_back.domain.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gitbaby.happy_back.domain.common.exception.RedisParseException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisUtil {
  private final StringRedisTemplate redisTemplate;
  private final ObjectMapper objectMapper;

  // 키,값, 만료시간 등록
  public void set(String key, String value, long timeout, TimeUnit timeUnit) {
    redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
  }

  // 값 조회
  public String get(String key) {
    return redisTemplate.opsForValue().get(key);
  }

  // 키 삭제
  public boolean delete(String key) {
    return redisTemplate.delete(key);
  }

  // 키 확인
  public boolean hasKey(String key) {
    return redisTemplate.hasKey(key);
  }

  // 오브젝트 저장
  public <T> void setObject (String key, T value, long ttl, TimeUnit ttlUnit) {
    try {
      String json = objectMapper.writeValueAsString(value);
      redisTemplate.opsForValue().set(key, json, ttl, ttlUnit);
    } catch (JsonProcessingException e) {
      throw new RedisParseException();
    }
  }

  // 오브젝트 가져오기
  public <T> T getObject(String key, Class<T> clazz) {
    String json = redisTemplate.opsForValue().get(key);
    if (json == null) return null;
    try {
      return objectMapper.readValue(json, clazz);
    } catch (JsonProcessingException e) {
      throw new RedisParseException();
    }
  }
}
