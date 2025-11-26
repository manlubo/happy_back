package com.gitbaby.happy_back.domain.member.controller;

import com.gitbaby.happy_back.domain.common.dto.ApiResponce;
import com.gitbaby.happy_back.domain.member.dto.MemberReadSignupEmailRequest;
import com.gitbaby.happy_back.domain.member.dto.MemberSignupEmailRequest;
import com.gitbaby.happy_back.domain.member.dto.MemberSignupRequest;
import com.gitbaby.happy_back.domain.member.dto.MemberSignupResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;


public interface AuthControllerSpec {
  @Operation(
    summary = "회원가입 전 인증메일 발송",
    description = "입력받은 이메일로 인증 링크를 발송합니다."
  )
  @ApiResponses({
    @ApiResponse(
      responseCode = "200",
      description = "인증 메일 발송 성공",
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = ApiResponce.class),
        examples = @ExampleObject("""
          {
            "success": true,
            "message": "인증 메일이 발송되었습니다."
          }
        """)
      )
    ),
    @ApiResponse(
      responseCode = "409",
      description = "이미 가입된 이메일",
      content = @Content(
        mediaType = "application/json",
        examples = @ExampleObject("""
          {
            "success": false,
            "errorCode": "EMAIL_EXISTS",
            "message": "이미 가입된 이메일입니다."
          }
        """)
      )
    )
  })
  ResponseEntity<?> sendSignupEmail(MemberSignupEmailRequest memberSignupEmailRequest);

  @Operation(
    summary = "회원가입 이메일 인증 확인",
    description = "프론트에서 전달받은 이메일 인증 토큰의 유효성을 검증합니다."
  )
  @ApiResponses({
    @ApiResponse(
      responseCode = "200",
      description = "인증 성공",
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = ApiResponce.class),
        examples = @ExampleObject("""
            {
              "success": true,
              "message": "이메일 인증이 완료되었습니다.",
              "data": {
                "email": "manlubo11@gmail.com"
              }
            }
            """)
      )
    ),
    @ApiResponse(
      responseCode = "400",
      description = "인증 토큰 누락",
      content = @Content(
        mediaType = "application/json",
        examples = @ExampleObject("""
            {
              "success": false,
              "errorCode": "INVALID_EMAIL_TOKEN",
              "message": "이메일 인증 토큰이 누락되었습니다."
            }
            """)
      )
    ),
    @ApiResponse(
      responseCode = "410",
      description = "만료되었거나 잘못된 인증 토큰",
      content = @Content(
        mediaType = "application/json",
        examples = @ExampleObject("""
            {
              "success": false,
              "errorCode": "EMAIL_VERIFICATION_EXPIRED",
              "message": "이메일 인증 정보가 만료되었거나 잘못된 값입니다."
            }
            """)
      )
    )
  })
  ResponseEntity<?> readSignupEmail(MemberReadSignupEmailRequest memberReadSignupEmailRequest);

  @Operation(
    summary = "회원가입",
    description = """
      이메일 인증 토큰을 기반으로 회원가입을 처리합니다.

      - 이메일 인증 토큰이 없으면 INVALID_EMAIL_TOKEN 발생
      - 토큰이 만료되었거나 존재하지 않으면 EMAIL_VERIFICATION_EXPIRED 발생
      - 인증된 이메일과 요청 이메일이 다르면 EMAIL_MISMATCH 발생
      - ORG → READY, USER → ACTIVE 상태로 가입 처리
  """
  )
  @ApiResponses({
    @ApiResponse(
      responseCode = "200",
      description = "회원가입 성공",
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = MemberSignupResponse.class),
        examples = @ExampleObject("""
        {
          "success": true,
          "message": "회원가입 성공",
          "data": {
            "memberId": 3,
            "roles": ["USER"]
          }
        }
      """)
      )
    ),
    @ApiResponse(
      responseCode = "400",
      description = "잘못된 요청",
      content = @Content(
        mediaType = "application/json",
        examples = {
          @ExampleObject(
            name = "INVALID_EMAIL_TOKEN",
            summary = "이메일 인증 토큰 누락",
            value = """
            {
              "success": false,
              "errorCode": "INVALID_EMAIL_TOKEN",
              "message": "이메일 인증용 토큰이 필요합니다."
            }
          """
          ),
          @ExampleObject(
            name = "EMAIL_VERIFICATION_EXPIRED",
            summary = "이메일 인증 정보 만료됨",
            value = """
            {
              "success": false,
              "errorCode": "EMAIL_VERIFICATION_EXPIRED",
              "message": "이메일 인증 정보가 만료되었습니다."
            }
          """
          ),
          @ExampleObject(
            name = "EMAIL_MISMATCH",
            summary = "인증 이메일과 요청 이메일 불일치",
            value = """
            {
              "success": false,
              "errorCode": "EMAIL_MISMATCH",
              "message": "이메일 정보가 일치하지 않습니다."
            }
          """
          )
        }
      )
    )
  })
  ResponseEntity<?> signup(MemberSignupRequest memberSignupRequest, String token);
}
