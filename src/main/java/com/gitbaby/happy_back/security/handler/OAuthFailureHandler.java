package com.gitbaby.happy_back.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Log4j2
public class OAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

  private final ObjectMapper objectMapper;

  @Override
  public void onAuthenticationFailure(HttpServletRequest request,
                                      HttpServletResponse response,
                                      AuthenticationException exception)
    throws IOException, ServletException {


    log.warn("소셜 로그인 실패: {}", exception.getMessage());

    String userMessage = convertErrorMessage(exception);

    String json = objectMapper.writeValueAsString(Map.of(
      "status", "OAUTH_FAILED",
      "message", userMessage
    ));

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

  private String convertErrorMessage(AuthenticationException exception) {
    String msg = exception.getMessage();

    if (msg.contains("not found")) {
      return "인증이 정상적으로 처리되지 않았습니다. 다시 시도해주세요.";
    }
    if (msg.contains("access_denied")) {
      return "소셜 로그인이 취소되었습니다.";
    }

    // 기본 메시지
    return "소셜 로그인에 실패했습니다. 다시 시도해주세요.";
  }
}

