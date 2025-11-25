package com.gitbaby.happy_back.domain.member.controller;

import com.gitbaby.happy_back.domain.common.dto.ApiResponce;
import com.gitbaby.happy_back.domain.member.dto.MemberReadSignupEmailRequest;
import com.gitbaby.happy_back.domain.member.dto.MemberSignupEmailRequest;
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
}
