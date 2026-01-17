package com.gitbaby.happy_back.domain.common.service;

import com.gitbaby.happy_back.domain.common.util.MailUtil;
import com.gitbaby.happy_back.domain.common.util.SmsUtil;
import com.gitbaby.happy_back.domain.member.en.SignupRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncService {
  private final MailUtil mailUtil;
  private final SmsUtil smsUtil;

  @Async
  public void signupEmailVerification(String email, String emailVerificationToken, SignupRole role) {
    mailUtil.signupEmailVerification(email, emailVerificationToken, role);
  }

  @Async
  public void passwordResetEmailVerification(String email, String emailVerificationToken) {
    mailUtil.passwordResetEmailVerification(email, emailVerificationToken);
  }

  @Async
  public void sendSms(String tel, String token){
    smsUtil.sendSms(tel, token);
  }

}
