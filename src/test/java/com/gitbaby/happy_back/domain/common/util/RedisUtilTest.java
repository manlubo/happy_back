package com.gitbaby.happy_back.domain.common.util;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class RedisUtilTest {
  @Autowired
  private RedisUtil redisUtil;

  @Test
  void set() {
    redisUtil.set("1", "23", 1L, TimeUnit.MINUTES);
  }

  @Test
  void get() {
    log.info(redisUtil.get("1"));
  }

  @Test
  void delete() {
    redisUtil.delete("1");
  }

  @Test
  void hasKey() {
    log.info(redisUtil.hasKey("1"));
  }

  @Test
  void setObject() {
    redisUtil.setObject("1", Map.of("1", "2", "3", "4"), 1L, TimeUnit.MINUTES);
  }

  @Test
  void getObject() {
    log.info(redisUtil.getObject("1", Map.class));
  }
}