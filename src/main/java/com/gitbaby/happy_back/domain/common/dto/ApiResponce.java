package com.gitbaby.happy_back.domain.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponce<T> {
  private boolean success;
  private String message;
  private String code;
  private T data;

  // 성공 응답
  public static <T> ApiResponce<T> success(String message, T data) {
    return new ApiResponce<>(true, message, null, data);
  }

  // 실패 응답
  public static ApiResponce<?> fail(String code, String message) {
    return new ApiResponce<>(false, message, code, null);
  }
}
