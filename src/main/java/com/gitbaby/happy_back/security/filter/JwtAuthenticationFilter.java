package com.gitbaby.happy_back.security.filter;

import com.gitbaby.happy_back.security.dto.MemberAuthDTO;
import com.gitbaby.happy_back.security.service.CustomUserDetailService;
import com.gitbaby.happy_back.security.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtUtil jwtUtil;
  private final CustomUserDetailService customUserDetailService;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain)
    throws ServletException, IOException {

    String accessToken = resolveAccessToken(request);

    // 엑세스 토큰 없거나 인증된 사용자면 바로 필터체인
    if (accessToken == null || SecurityContextHolder.getContext().getAuthentication() != null) {
      filterChain.doFilter(request, response);
      return;
    }

    try {
      // Claims 파싱
      String memberId = jwtUtil.getClaims(accessToken).getSubject();

      // 인증 세팅
      setAuthentication(memberId, request, response);

      if (response.isCommitted()) {
        return;
      }

      log.info("JWT 인증 성공 : {}", memberId);

    } catch (ExpiredJwtException ex) {
      writeJsonError(response, 401, "TOKEN_EXPIRED", "Access Token이 만료되었습니다.");
      return;

    } catch (io.jsonwebtoken.security.SignatureException ex) {
      writeJsonError(response, 401, "TOKEN_INVALID", "서명이 위조된 토큰입니다.");
      return;

    } catch (io.jsonwebtoken.MalformedJwtException ex) {
      writeJsonError(response, 401, "TOKEN_MALFORMED", "잘못된 JWT 형식입니다.");
      return;

    } catch (io.jsonwebtoken.security.SecurityException ex) {
      writeJsonError(response, 401, "TOKEN_SECURITY", "JWT 보안 오류가 발생했습니다.");
      return;

    } catch (JwtException ex) {
      writeJsonError(response, 401, "TOKEN_ERROR", "JWT 처리 중 오류가 발생했습니다.");
      return;

    } catch (Exception ex) {
      writeJsonError(response, 500, "SERVER_ERROR", "서버 오류가 발생했습니다.");
      return;
    }

    filterChain.doFilter(request, response);
  }

  // 컨텍스트에 멤버 등록
  private void setAuthentication(String memberId, HttpServletRequest request, HttpServletResponse response) throws IOException {

    MemberAuthDTO member =
      (MemberAuthDTO) customUserDetailService.loadUserByUsername(memberId);

    if (member == null) {
      writeJsonError(response,
        HttpServletResponse.SC_UNAUTHORIZED,
        "INVALID_USER",
        "유효하지 않은 사용자입니다."
      );
      return;
    }

    UsernamePasswordAuthenticationToken auth =
      new UsernamePasswordAuthenticationToken(
        member,
        null,
        member.getAuthorities()
      );

    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(auth);
  }


  // 리퀘스트에서 엑세스토큰 값 파싱
  private String resolveAccessToken(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  // Json 에러 공통처리
  private void writeJsonError(HttpServletResponse response,
                              int status,
                              String code,
                              String message) throws IOException {

    response.setStatus(status);
    response.setContentType("application/json;charset=UTF-8");
    response.getWriter().write("""
                {
                  "errorCode": "%s",
                  "message": "%s"
                }
                """.formatted(code, message));
  }
}
