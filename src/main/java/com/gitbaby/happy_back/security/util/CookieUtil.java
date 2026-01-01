package com.gitbaby.happy_back.security.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CookieUtil {
  @Value("${jwt.refresh-expiration-days}")
  private long expireDays;

  @Value("${jwt.expiration-minutes}")
  private long expirationMinutes;

  @Value("${cookie.secure}")
  private boolean secure;

  @Value("${cookie.domain}")
  private String domain;

  @Value("${cookie.same-site}")
  private String sameSite;

  // 쿠키 공통 로직
  private ResponseCookie.ResponseCookieBuilder baseCookie(String name, String value) {

    ResponseCookie.ResponseCookieBuilder builder = ResponseCookie
        .from(name, value)
        .httpOnly(true)
        .secure(secure)
        .sameSite(sameSite)
        .path("/");

    if (domain != null && !domain.isBlank()) {
      builder.domain(domain);
    }

    return builder;
  }

  // 엑세스 쿠키 생성
  public ResponseCookie createAccessCookie(String accessToken) {
    return baseCookie("HP_ACCESS", accessToken)
        .maxAge(expirationMinutes * 60)
        .build();
  }

  // 엑세스 쿠키 삭제
  public ResponseCookie deleteAccessCookie() {
    return baseCookie("HP_ACCESS", "")
        .maxAge(0)
        .build();
  }

  // 리프레쉬 쿠키 생성
  public ResponseCookie createRefreshCookie(String refreshToken, boolean rememberMe) {
    long maxAge = rememberMe ? expireDays * 24 * 60 * 60 : -1;

    return baseCookie("HP_REFRESH", refreshToken)
        .maxAge(maxAge)
        .build();
  }

  // 리프레쉬 쿠키 삭제
  public ResponseCookie deleteRefreshCookie() {
    return baseCookie("HP_REFRESH", "")
        .maxAge(0)
        .build();
  }

  // 로그인시 사용(쿠키 전체 발급)
  public List<ResponseCookie> createLoginCookies(String accessToken, String refreshToken, boolean rememberMe) {
    return List.of(
        createAccessCookie(accessToken),
        createRefreshCookie(refreshToken, rememberMe));
  }

  // 로그아웃시 사용(쿠키 전체 삭제)
  public List<ResponseCookie> createLogoutCookies() {
    return List.of(
        deleteAccessCookie(),
        deleteRefreshCookie());
  }

  // 쿠키 존재 여부 확인
  public boolean hasCookie(HttpServletRequest request, String cookieName) {
    return getCookieValue(request, cookieName) != null;
  }

  // 쿠기 값 읽기
  public String getCookieValue(HttpServletRequest request, String cookieName) {
    if (request.getCookies() == null) {
      return null;
    }

    for (Cookie cookie : request.getCookies()) {
      if (cookie.getName().equals(cookieName)) {
        return cookie.getValue();
      }
    }

    return null;
  }
}