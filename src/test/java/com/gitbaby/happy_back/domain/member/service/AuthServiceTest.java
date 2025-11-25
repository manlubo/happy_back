package com.gitbaby.happy_back.domain.member.service;

import com.gitbaby.happy_back.domain.member.dto.MemberReadSignupEmailRequest;
import com.gitbaby.happy_back.domain.member.dto.MemberSignupEmailRequest;
import com.gitbaby.happy_back.domain.member.dto.MemberSignupRequest;
import com.gitbaby.happy_back.domain.member.en.SignupRole;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Log4j2
class AuthServiceTest {
  @Autowired
  private AuthService authService;

  @Test
  @DisplayName("회원가입 메일전송 테스트")
  void signUpEmailVerification() {
    MemberSignupEmailRequest memberSignupEmailRequest = new MemberSignupEmailRequest("manlubo11@gmail.com", SignupRole.ORG);
    log.info(authService.signupEmailVerification(memberSignupEmailRequest));

  }

  @Test
  @DisplayName("메일 인증 확인 테스트")
  void signupEmailVerified(){
    log.info(authService.signupEmailVerified(new MemberReadSignupEmailRequest("e59baf5a-4b15-4d47-a269-1e2d700cae31")));
  }

  @Test
  @Transactional
  @DisplayName("회원가입 테스트")
  void userSignup() {
    MemberSignupRequest memberSignupRequest = new MemberSignupRequest("manlubo11@gmail.com", "12345678", "전상현", "010-6687-8628", "서울특별시 금천구 독산동", SignupRole.ORG);
    log.info(authService.signup(memberSignupRequest, "8238696d-d687-40dd-a664-cc142aec3dd0"));
  }
}