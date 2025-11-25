package com.gitbaby.happy_back.domain.member.controller;

import com.gitbaby.happy_back.domain.common.dto.ApiResponce;
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
}
