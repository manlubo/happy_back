package com.gitbaby.happy_back.domain.member.dto;

import com.gitbaby.happy_back.domain.member.en.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@Getter
@AllArgsConstructor
@ToString
public class MemberSignupResponse {
  @NotNull(message = "아이디는 필수 입력값입니다.")
  Long memberId;

  @NotEmpty(message = "역할은 필수 입력값입니다.")
  private Set<Role> roles;
}
