package com.gitbaby.happy_back.domain.member.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseCookie;

import java.util.List;

@Getter
@AllArgsConstructor
public class MemberCookieWithLoginResponse {
  @NotNull
  MemberLoginResponse memberLoginResponse;

  @NotEmpty
  List<ResponseCookie> cookies;
}
