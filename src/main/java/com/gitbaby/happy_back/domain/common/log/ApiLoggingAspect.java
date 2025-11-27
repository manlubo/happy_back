package com.gitbaby.happy_back.domain.common.log;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Aspect
@Component
@Log4j2
public class ApiLoggingAspect {

  private final Statistics statistics;

  public ApiLoggingAspect(SessionFactory sessionFactory) {
    this.statistics = sessionFactory.getStatistics();
    this.statistics.setStatisticsEnabled(true);
  }

  @Around("execution(* com.gitbaby.happy_back..*Controller.*(..))")
  public Object logApiExecutionAndDbTime(ProceedingJoinPoint joinPoint) throws Throwable {

    // DB 통계 초기화
    statistics.clear();

    long apiStart = System.currentTimeMillis();
    Object result = joinPoint.proceed();
    long apiEnd = System.currentTimeMillis();

    long apiDuration = apiEnd - apiStart;

    // 실제 실행된 쿼리 개수
    long queryCount = statistics.getQueryExecutionCount();

    // 가장 오래 걸린 쿼리
    String maxQueryString = statistics.getQueryExecutionMaxTimeQueryString();

    // 가장 오래 걸린 쿼리 시간
    long maxQueryTime = statistics.getQueryExecutionMaxTime();

    log.info("""
                [API 성능 로그]
                - API: {}
                - API 전체 실행 시간: {} ms
                - DB 실행 쿼리 개수: {}
                - 가장 오래 걸린 쿼리:
                {}
                - 가장 오래 걸린 DB 쿼리 시간: {} ms
                """,
      joinPoint.getSignature().toShortString(),
      apiDuration,
      queryCount,
      maxQueryString,
      maxQueryTime
    );

    return result;
  }
}
