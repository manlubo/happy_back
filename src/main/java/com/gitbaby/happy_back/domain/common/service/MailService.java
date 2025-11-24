package com.gitbaby.happy_back.domain.common.service;

import com.gitbaby.happy_back.domain.common.util.MailUtil;
import com.gitbaby.happy_back.domain.member.en.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
  private final MailUtil mailUtil;

  @Value("${custom.frontend-url}")
  private String frontendUrl;

  public void signupEmailVerification(String email, String emailVerificationToken, Role role) {

    String verificationUrl = frontendUrl + "/signup/" + role.toString().toLowerCase() + "?token=" + emailVerificationToken;

    String title = "귀하의 해피기버즈 회원가입을 위해 이메일 주소를 확인해 주십시오.";
    String content = buildEmail(
      "회원가입 이메일 인증",
      "아래 버튼을 클릭하여 회원가입을 계속 진행해주세요.",
      "회원가입 진행하기",
      verificationUrl
    );


    mailUtil.sendMail(email, title, content);
  }


  public void passwordResetEmailVerification(String email, String emailVerificationToken) {

    String verificationUrl = frontendUrl + "/reset-pw?token=" + emailVerificationToken;

    String title = "귀하의 해피기버즈 계정 비밀번호를 변경해 주십시오.";
    String content = buildEmail(
      "계정 비밀번호 재설정",
      "아래 버튼을 클릭하여 비밀번호를 재설정해 주세요. 링크는 20분간 유효합니다.",
      "비밀번호 재설정",
      verificationUrl
    );

    mailUtil.sendMail(email, title, content);
  }



  private String buildEmail(String title, String message, String buttonText, String buttonUrl) {
    return "<body style=\"margin:0; padding:0; background-color:#f9f9f9; font-family:Arial, sans-serif;\">"
      + "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" "
      + "style=\"background-color:#f9f9f9; padding:20px 0; max-width:400px; margin:0 auto;\">"

      // 카드 영역
      + "<tr><td align=\"center\">"
      + "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" "
      + "style=\"background-color:#ffffff; border-radius:12px; border:1px solid #e0e0e0; overflow:hidden;\">"

      // 본문
      + "<tr><td style=\"padding:0 20px 20px; text-align:center;\">"
      + "<h2 style=\"margin:20px 0; font-size:18px; color:#000000;\">" + title + "</h2>"
      + "<p style=\"margin:0 0 30px; font-size:14px; color:#333333; line-height:1.6;\">" + message + "</p>"
      + buildButton(buttonText, buttonUrl)
      + "</td></tr>"

      // 푸터
      + "<tr><td style=\"padding:20px; text-align:center; background-color:#f9f9f9; border-top:1px solid #e0e0e0;\">"
      + buildFooter()
      + "</td></tr>"

      + "</table></td></tr></table></body>";
  }



  private String buildButton(String text, String url) {
    return "<a href=\"" + url + "\" "
      + "style=\"display:inline-block; width:100%; max-width:360px; text-align:center;"
      + " padding:12px 0; background-color:#2563eb; color:#ffffff; text-decoration:none;"
      + " border-radius:8px; font-size:14px; font-weight:bold;\">"
      + text
      + "</a>";
  }

  private String buildFooter() {
    return "<p style=\"margin:0; font-size:12px; color:#888888;\">© 2025 Happygivers. All rights reserved.</p>";
  }

}
