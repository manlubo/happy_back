package com.gitbaby.happy_back.domain.member.controller;

import com.gitbaby.happy_back.domain.member.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Auth", description = "인증 관련 API")
public interface AuthControllerSpec {
    @Operation(summary = "회원가입 전 인증메일 발송", description = "입력받은 이메일로 인증 링크를 발송합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "인증 메일 발송 성공"),
            @ApiResponse(responseCode = "409", description = "이미 가입된 이메일")
    })
    ResponseEntity<?> sendSignupEmail(@RequestBody MemberSignupEmailRequest memberSignupEmailRequest);

    @Operation(summary = "회원가입 이메일 인증 확인", description = "프론트에서 전달받은 이메일 인증 토큰의 유효성을 검증합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "인증 성공"),
            @ApiResponse(responseCode = "400", description = "인증 토큰 누락"),
            @ApiResponse(responseCode = "410", description = "만료되었거나 잘못된 인증 토큰")
    })
    ResponseEntity<?> readSignupEmail(@RequestBody MemberReadSignupEmailRequest memberReadSignupEmailRequest);

    @Operation(summary = "회원가입", description = "이메일 인증 완료 후 회원가입을 처리합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    ResponseEntity<?> signup(@RequestBody MemberSignupRequest memberSignupRequest, @PathVariable("token") String token);

    @Operation(summary = "로그인", description = "이메일/휴대폰 번호와 비밀번호로 로그인합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "400", description = "로그인 실패")
    })
    ResponseEntity<?> login(@RequestBody MemberLoginRequest memberLoginRequest);

    @Operation(summary = "로그아웃", description = "로그인한 사용자의 세션을 종료합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그아웃 성공"),
            @ApiResponse(responseCode = "400", description = "로그아웃 실패")
    })
    ResponseEntity<?> logout();

    @Operation(summary = "회원가입 전 인증메일 발송", description = "입력받은 이메일로 인증 링크를 발송합니다.")
    @ApiResponses({
      @ApiResponse(responseCode = "200", description = "인증 문자 발송 성공"),
      @ApiResponse(responseCode = "400", description = "문자 발송 실패")
    })
    ResponseEntity<?> sendSms(@RequestBody MemberSendSmsRequest memberSendSmsRequest);

    ResponseEntity<?> verifiedSms(@RequestBody MemberSmsVerifiedRequest memberSmsVerifiedRequest);
}
