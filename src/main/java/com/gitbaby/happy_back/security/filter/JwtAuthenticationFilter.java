package com.gitbaby.happy_back.security.filter;

import com.gitbaby.happy_back.security.dto.MemberAuthDTO;
import com.gitbaby.happy_back.security.exception.TokenUnauthorizedException;
import com.gitbaby.happy_back.security.service.CustomUserDetailService;
import com.gitbaby.happy_back.security.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
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

    String bearerToken = getBearerToken(request);
    String accessToken = resolveAccessToken(bearerToken);

    if (accessToken == null) {
      filterChain.doFilter(request, response);
      return;
    }

    if (SecurityContextHolder.getContext().getAuthentication() == null) {
      try {
        String memberId = jwtUtil.getClaims(accessToken).getSubject();
        setAuthentication(memberId, request);
        log.info("JWT 인증 성공 : {}", memberId);
      } catch (ExpiredJwtException ex) {
        log.warn("Access Token 만료됨");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("""
            {
              "errorCode": "TOKEN_EXPIRED",
              "message": "Access Token이 만료되었습니다."
            }
        """);
        return;
      } catch (Exception e) {
        log.error("JWT 파싱/검증 실패", e);
        throw new TokenUnauthorizedException();
      }
    }

    filterChain.doFilter(request, response);

  }


  private void setAuthentication(String memberId, HttpServletRequest request) {
    MemberAuthDTO member =
      (MemberAuthDTO) customUserDetailService.loadUserByUsername(memberId);

    UsernamePasswordAuthenticationToken authentication =
      new UsernamePasswordAuthenticationToken(
        member,
        null,
        member.getAuthorities()
      );

    authentication.setDetails(
      new WebAuthenticationDetailsSource().buildDetails(request)
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  // 헤더에서 엑세스토큰 가져오기
  private String getBearerToken(HttpServletRequest request) {
    return request.getHeader("Authorization");
  }

  // 헤더에서 엑세스토큰 값 파싱
  private String resolveAccessToken(String bearerToken) {
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
}
