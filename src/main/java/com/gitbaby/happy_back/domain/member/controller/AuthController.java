package com.gitbaby.happy_back.domain.member.controller;

import com.gitbaby.happy_back.domain.common.dto.ApiResponce;
import com.gitbaby.happy_back.domain.member.dto.*;
import com.gitbaby.happy_back.domain.member.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
@Log4j2
public class AuthController implements AuthControllerSpec {
  private final AuthService authService;

  @Override
  @PostMapping("signup/email/verification")
  public ResponseEntity<?> sendSignupEmail(@RequestBody @Valid MemberSignupEmailRequest req) {
    authService.signupEmailVerification(req);
    return ResponseEntity.ok(ApiResponce.success("인증 메일이 발송되었습니다.", null));
  }

  @Override
  @PostMapping("signup/email/verification/check")
  public ResponseEntity<?> readSignupEmail(@RequestBody @Valid MemberReadSignupEmailRequest req) {
    return ResponseEntity.ok(ApiResponce.success("이메일 인증 성공", authService.signupEmailVerified(req)));
  }

  @Override
  @PostMapping("signup/{token}")
  public ResponseEntity<?> signup(@RequestBody @Valid MemberSignupRequest memberSignupRequest,
      @PathVariable("token") String token) {
    return ResponseEntity.ok(ApiResponce.success("회원가입 성공", authService.signup(memberSignupRequest, token)));
  }

  @Override
  @PostMapping("login")
  public ResponseEntity<?> login(@RequestBody @Valid MemberLoginRequest memberLoginRequest) {
    MemberCookieWithLoginResponse res = authService.login(memberLoginRequest);

    ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
    res.getCookies().forEach(cookie -> builder.header(HttpHeaders.SET_COOKIE, cookie.toString()));

    return builder.body(ApiResponce.success("로그인 성공", res.getMemberLoginResponse()));
  }
}
