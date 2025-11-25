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
  private T data;
  private String errorCode;

  // 성공 응답
  public static <T> ApiResponce<T> success(String message, T data){
    return new ApiResponce<>(true, message, data, null);
  }

  // 실패 응답
  public static ApiResponce<?> fail(String message, String errorCode){
    return new ApiResponce<>(false, message, null, errorCode);
  }
}
