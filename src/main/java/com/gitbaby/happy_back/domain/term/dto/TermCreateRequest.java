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
public class TermCreateRequest {
  @NotNull(message = "약관 타입은 필수 입력항목입니다.")
  private TermType type;

  @NotBlank(message = "약관 내용은 필수 입력항목입니다.")
  private String content;

  @NotNull(message = "필수여부는 필수 입력항목입니다.")
  private boolean required;
}
