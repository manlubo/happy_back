package com.gitbaby.happy_back.domain.member.dto;

import com.gitbaby.happy_back.domain.member.en.MemberStatus;
import com.gitbaby.happy_back.domain.member.en.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class MemberLoginResponse {
  @NotNull
  private Long id;

  @NotBlank
  private String name;

  @NotNull
  private Set<Role> roles;

  @NotNull
  private MemberStatus status;

  private String profile;
}
