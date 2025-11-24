package com.gitbaby.happy_back.domain.member.service;

import com.gitbaby.happy_back.domain.member.dto.MemberSignupEmailRequest;
import com.gitbaby.happy_back.domain.member.dto.MemberSignupRequest;
import com.gitbaby.happy_back.domain.member.en.Role;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class AuthServiceTest {
  @Autowired
  private AuthService authService;

  @Test
  @DisplayName("회원가입 메일전송 테스트")
  void signUpEmailVerification() {
    MemberSignupEmailRequest memberSignupEmailRequest = new MemberSignupEmailRequest("manlubo11@gmail.com", Role.ORG);
    log.info(authService.signUpEmailVerification(memberSignupEmailRequest));

  }

  @Test
  @Transactional
  @DisplayName("회원가입 테스트")
  void userSignup() {
    MemberSignupRequest memberSignupRequest = new MemberSignupRequest("manlubo11@gmail.com", "12345678", "전상현", "010-6687-8628", "서울특별시 금천구 독산동", Role.ORG);
    log.info(authService.signup(memberSignupRequest, "426424e8-e26a-444c-bbba-4cef1e4b8112"));
  }
}