package com.gitbaby.happy_back.domain.common.util;

import com.gitbaby.happy_back.domain.common.exception.MailSendException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
@RequiredArgsConstructor
public class MailUtil {

  @Value("${spring.mail.username}")
  private String from;
  private final JavaMailSender mailSender;

  @Async
  public void sendMail(String email, String title, String content) {
    try {
      MimeMessage mimeMessage = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

      helper.setTo(email);
      helper.setSubject(title);
      helper.setText(content, true);
      helper.setFrom(from, "HappyGivers Team");

      mailSender.send(mimeMessage);
    } catch (MessagingException | UnsupportedEncodingException e) {
      throw new MailSendException(e);
    }
  }

}