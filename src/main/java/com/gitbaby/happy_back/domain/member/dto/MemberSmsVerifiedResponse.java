package com.gitbaby.happy_back.domain.member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class MemberSmsVerifiedResponse {
  @NotNull(message = "이메일은 필수 입력값입니다.")
  String email;
}
