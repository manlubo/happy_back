package com.gitbaby.happy_back.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class MemberReadSignupEmailRequest {
  @NotBlank
  private String token;
}
