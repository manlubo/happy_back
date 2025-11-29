package com.gitbaby.happy_back.domain.term.dto;

import com.gitbaby.happy_back.domain.term.en.TermType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class TermCreateResponse {
  @NotNull(message = "약관 타입은 필수 입력항목입니다.")
  private TermType type;

  @NotBlank(message = "약관 버전은 필수 입력항목입니다.")
  private String version;

}
