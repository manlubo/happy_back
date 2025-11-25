package com.gitbaby.happy_back.domain.member.dto;

import com.gitbaby.happy_back.domain.member.en.SignupRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class MemberSignupEmailRequest {
  @NotBlank
  @Email(message = "올바른 이메일 형식을 입력해주세요.")
  private String email;

  @NotNull
  private SignupRole role;
}
