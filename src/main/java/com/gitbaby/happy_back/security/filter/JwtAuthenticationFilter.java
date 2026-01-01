package com.gitbaby.happy_back.security.filter;

import com.gitbaby.happy_back.security.dto.MemberAuthDTO;
import com.gitbaby.happy_back.security.service.CustomUserDetailService;
import com.gitbaby.happy_back.security.util.CookieUtil;
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

  private final CookieUtil cookieUtil;
  private final JwtUtil jwtUtil;
  private final CustomUserDetailService customUserDetailService;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain)
      throws ServletException, IOException {

    String accessToken = cookieUtil.getCookieValue(request, "HP_ACCESS");

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

    } catch (JwtException ex) {
      log.error("JWT 인증 실패");
    }

    filterChain.doFilter(request, response);
  }

  // 컨텍스트에 멤버 등록
  private void setAuthentication(String memberId, HttpServletRequest request, HttpServletResponse response) {

    MemberAuthDTO member = (MemberAuthDTO) customUserDetailService.loadUserByUsername(memberId);

    if (member == null) {
      return;
    }

    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
        member,
        null,
        member.getAuthorities());

    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(auth);
  }

}
