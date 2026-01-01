package com.gitbaby.happy_back.security.util;

import com.gitbaby.happy_back.domain.member.en.MemberStatus;
import com.gitbaby.happy_back.domain.member.en.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.*;

@Component
public class JwtUtil {
  private final SecretKey key;

  public JwtUtil(@Value("${jwt.secret}") String secret) {
    key = Keys.hmacShaKeyFor(secret.getBytes());
  }

  @Value("${jwt.expiration-minutes}")
  private long expireMinutes;

  @Value("${jwt.refresh-expiration-days}")
  private long expireDays;

  // JWT 토큰 생성
  private String generateToken(Map<String, Object> claims, String subject, long expireSeconds) {
    Instant now = Instant.now();
    Date issuedAt = Date.from(now);
    Date expiredAt = Date.from(now.plusSeconds(expireSeconds));

    return Jwts.builder()
        .claims(claims)
        .subject(subject)
        .issuedAt(issuedAt)
        .expiration(expiredAt)
        .signWith(key)
        .compact();
  }

  // 엑세스 토큰 생성
  public String createAccessToken(Long memberId, MemberStatus status, Set<Role> roles) {
    long expireSeconds = expireMinutes * 60;

    Map<String, Object> claims = new HashMap<>();
    claims.put("status", status);
    claims.put("roles", roles);

    return generateToken(claims, memberId.toString(), expireSeconds);
  }

  // 리프레시 토큰 생성
  public String createRefreshToken(Long memberId) {
    long expireSeconds = expireDays * 24 * 60 * 60;
    return generateToken(Collections.emptyMap(), memberId.toString(), expireSeconds);
  }

  // Jwt 토큰값 파싱
  public Claims getClaims(String token) {
    return Jwts.parser()
        .verifyWith(key)
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  // 토큰 유효성 검증
  public Long getTokenTTL(String token) {
    try {
      Claims claims = Jwts.parser()
          .verifyWith(key)
          .build()
          .parseSignedClaims(token)
          .getPayload();

      // 남은 시간 계산
      long exp = claims.getExpiration().getTime();
      long now = System.currentTimeMillis();
      long ttl = exp - now;

      // 음수면 0으로 처리
      return ttl > 0 ? ttl : 0L;

    } catch (ExpiredJwtException e) {
      // 만료되었을 때
      return 0L;
    } catch (JwtException | IllegalArgumentException e) {
      // 토큰 위조/구조 문제
      return null;
    }
  }
}
