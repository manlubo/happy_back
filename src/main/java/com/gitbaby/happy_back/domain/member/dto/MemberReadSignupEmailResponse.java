package com.gitbaby.happy_back.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class MemberReadSignupEmailResponse {
  @NotBlank
  @Email(message = "올바른 이메일 형식을 입력해주세요.")
  private String email;
}
