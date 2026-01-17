package com.gitbaby.happy_back.domain.member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class MemberSendSmsRequest {
  @NotNull(message = "전화번호는 필수 입력값입니다.")
  String tel;
}
