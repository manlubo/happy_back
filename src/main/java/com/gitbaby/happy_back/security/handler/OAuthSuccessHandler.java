package com.gitbaby.happy_back.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gitbaby.happy_back.security.dto.MemberAuthDTO;
import com.gitbaby.happy_back.security.dto.SocialProcessDTO;
import com.gitbaby.happy_back.security.dto.SocialResult;
import com.gitbaby.happy_back.security.en.SocialProcessType;
import com.gitbaby.happy_back.security.util.CookieUtil;
import com.gitbaby.happy_back.security.util.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
  private final JwtUtil jwtUtil;
  private final CookieUtil cookieUtil;
  private final ObjectMapper objectMapper;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    Object data = ((SocialResult) authentication.getPrincipal()).getData();
    String json = objectMapper.writeValueAsString(Map.of("status", "UNKNOWN"));

    if (data instanceof MemberAuthDTO member) {
      // 토큰 생성
      String accessToken = jwtUtil.createAccessToken(member.getId(), member.getStatus(), member.getRoles());
      String refreshToken = jwtUtil.createRefreshToken(member.getId());

      // 쿠키생성
      List<ResponseCookie> cookies = cookieUtil.createLoginCookies(accessToken, refreshToken, true);
      cookies.forEach(cookie -> response.addHeader("Set-Cookie", cookie.toString()));

      json = objectMapper.writeValueAsString(Map.of(
        "status", "LOGIN_SUCCESS"
      ));
    }

    if (data instanceof SocialProcessDTO process) {
      if (SocialProcessType.SIGNUP.equals(process.getType())) {
        json = objectMapper.writeValueAsString(Map.of(
          "status", "SIGNUP_REQUIRED",
          "socialUser", process.getSocialUser()
        ));
      }
      if (SocialProcessType.FORBIDDEN_EMAIL.equals(process.getType())) {
        json = objectMapper.writeValueAsString(Map.of(
          "status", "EMAIL_EXISTS",
          "message", "이미 가입된 이메일입니다."
        ));
      }
    }

    response.setContentType("text/html;charset=UTF-8");
    response.getWriter().write(fallbackScript(json));
  }


  private String fallbackScript(String json){
    return """
        <script>
          window.opener.postMessage(%s, window.opener.location.origin);
          window.close();
        </script>
    """.formatted(json);
  }
}
