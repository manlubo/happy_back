package com.gitbaby.happy_back.domain.member.service;

import com.gitbaby.happy_back.domain.common.service.MailService;
import com.gitbaby.happy_back.domain.common.util.RedisUtil;
import com.gitbaby.happy_back.domain.member.dto.*;
import com.gitbaby.happy_back.domain.member.entity.Member;
import com.gitbaby.happy_back.domain.member.exception.*;
import com.gitbaby.happy_back.domain.member.mapper.MemberMapper;
import com.gitbaby.happy_back.security.util.CookieUtil;
import com.gitbaby.happy_back.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Log4j2
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final MemberService memberService;
  private final PasswordEncoder passwordEncoder;
  private final RedisUtil redisUtil;
  private final MailService mailService;

  private static final String EMAIL_VERIFICATION_PREFIX =  "EMAIL_VERIFICATION_TOKEN:";
  private final MemberMapper memberMapper;
  private final JwtUtil jwtUtil;
  private final CookieUtil cookieUtil;

  // 이메일 인증용 키 생성
  private String getEmailKey(String emailVerificationToken) {
    return EMAIL_VERIFICATION_PREFIX + emailVerificationToken;
  }

  // 이메일 인증 생성
  private String createEmailVerification(String email) {
    String emailVerificationToken = UUID.randomUUID().toString();
    redisUtil.set(getEmailKey(emailVerificationToken), email, 30L, TimeUnit.MINUTES);

    return emailVerificationToken;
  }

  // 이메일 인증 키 확인
  private boolean hasEmailVerification(String emailVerificationToken) {
    return redisUtil.hasKey(getEmailKey(emailVerificationToken));
  }

  // 이메일 인증 값 검증
  private String readEmailVerification(String emailVerificationToken) {
    return redisUtil.get(getEmailKey(emailVerificationToken));
  }

  // 이메일 인증 삭제
  private boolean deleteEmailVerification(String emailVerificationToken) {
    return redisUtil.delete(getEmailKey(emailVerificationToken));
  }

  // 회원가입 전 인증 메일 전송
  @Override
  public boolean signupEmailVerification(MemberSignupEmailRequest memberSignupEmailRequest) {
    // 이미 가입된 이메일일 때
    if(memberService.hasEmail(memberSignupEmailRequest.getEmail())){
      throw new EmailAlreadyExistsException();
    }

    String emailVerificationToken = createEmailVerification(memberSignupEmailRequest.getEmail());
    mailService.signupEmailVerification(memberSignupEmailRequest.getEmail(), emailVerificationToken, memberSignupEmailRequest.getRole());
    return hasEmailVerification(emailVerificationToken);
  }

  @Override
  public MemberReadSignupEmailResponse signupEmailVerified(MemberReadSignupEmailRequest memberReadSignupEmailRequest) {
    // 메일인증 토큰 없을 때
    if(memberReadSignupEmailRequest.getToken() == null){
      throw new InvalidEmailTokenException();
    }

    // 이메일 인증 정보 만료 or 잘못된 토큰일 때
    if(!hasEmailVerification(memberReadSignupEmailRequest.getToken())){
      throw new EmailVerificationExpiredException();
    }

    return new MemberReadSignupEmailResponse(readEmailVerification(memberReadSignupEmailRequest.getToken()));
  }

  // 회원가입 - 인증처리
  @Override
  public MemberSignupResponse signup(MemberSignupRequest memberSignupRequest, String emailVerificationToken) {

    // 메일인증 토큰 없을 때
    if(emailVerificationToken == null){
      throw new InvalidEmailTokenException();
    }

    // 이메일 인증 정보 만료되었을 때
    if(!hasEmailVerification(emailVerificationToken)){
      throw new EmailVerificationExpiredException();
    }

    // 인증한 이메일과, 입력된 이메일이 다를 때
    if(!readEmailVerification(emailVerificationToken).equals(memberSignupRequest.getEmail())) {
      throw new EmailMismatchException();
    }

    memberSignupRequest.setPassword(passwordEncoder.encode(memberSignupRequest.getPassword()));
    MemberSignupResponse resp = memberService.signup(memberSignupRequest);

    deleteEmailVerification(emailVerificationToken);

    return resp;
  }

  @Override
  public MemberCookieWithLoginResponse login(MemberLoginRequest memberLoginRequest) {
    Member member = memberService.getMemberByUsername(memberLoginRequest.getUsername());
    if (!member.getPassword().equals(passwordEncoder.encode(memberLoginRequest.getPassword()))) {
      throw new PasswordMismatchException();
    }

    MemberLoginResponse res = memberMapper.toMemberLoginResponse(member);

    String accessToken = jwtUtil.createAccessToken(res.getId(), res.getStatus(), res.getRoles());
    String refreshToken = jwtUtil.createRefreshToken(res.getId());

    List<ResponseCookie> cookies = cookieUtil.createLoginCookies(accessToken, refreshToken, memberLoginRequest.isRememberMe());

    return new MemberCookieWithLoginResponse(res, cookies);
  }
}
