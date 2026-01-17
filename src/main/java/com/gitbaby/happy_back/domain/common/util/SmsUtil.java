package com.gitbaby.happy_back.domain.common.util;

import com.gitbaby.happy_back.domain.common.exception.SmsSendException;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.HexFormat;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import java.time.Instant;
import org.springframework.http.*;

@Component
@RequiredArgsConstructor
public class SmsUtil {
  @Value("${solapi.api-key}")
  private String apiKey;

  @Value("${solapi.api-secret}")
  private String apiSecret;

  @Value("${solapi.base-url}")
  private String baseUrl;

  @Value("${solapi.from}")
  private String from;

  private final RedisUtil redisUtil;

  private final RestTemplate restTemplate = new RestTemplate();

  private String generateSignature(String date, String salt) throws Exception {
    String message = date + salt;
    Mac hmac = Mac.getInstance("HmacSHA256");
    hmac.init(new SecretKeySpec(apiSecret.getBytes(), "HmacSHA256"));
    byte[] hash = hmac.doFinal(message.getBytes());
    return HexFormat.of().formatHex(hash);
  }

  @Async
  public void sendSms(String to, String smsVerificationToken) {
    try {
      String date = Instant.now().toString();
      String salt = UUID.randomUUID().toString().replace("-", "");
      String signature = generateSignature(date, salt);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.set("Authorization", String.format(
        "HMAC-SHA256 apiKey=%s, date=%s, salt=%s, signature=%s",
        apiKey, date, salt, signature));

      Map<String, Object> message = new HashMap<>();
      message.put("to", to);
      message.put("from", from);
      message.put("text", "해피기버즈 인증번호 [" + smsVerificationToken + "]를 입력하세요.");
      message.put("type", "SMS");

      Map<String, Object> body = Map.of("message", message);
      HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

      restTemplate.postForEntity(
        baseUrl + "/messages/v4/send",
        request,
        String.class
      );
    } catch (Exception e) {
      throw new SmsSendException();
    }
  }
}
