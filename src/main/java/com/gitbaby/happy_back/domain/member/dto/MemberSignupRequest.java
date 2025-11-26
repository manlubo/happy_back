package com.gitbaby.happy_back.domain.member.dto;

import com.gitbaby.happy_back.domain.member.en.SignupRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class MemberSignupRequest {
  @NotBlank
  @Email(message = "올바른 이메일 형식을 입력해주세요.")
  private String email;

  @Setter
  @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
  @Size(min = 8, max = 20, message = "비밀번호는 8~20 자리여야합니다.")
  private String password;

  @NotBlank(message = "이름은 필수 입력 값입니다.")
  @Size(min = 2, message = "이름은 최소 두글자 이상이어야 합니다.")
  private String name;

  @NotBlank(message = "전화번호는 필수 입력 값입니다.")
  private String tel;

  @NotBlank(message = "주소는 필수 입력 값입니다.")
  private String address;

  @NotNull(message = "역할은 필수 입력값입니다.")
  private SignupRole role;

  // pdf 파일(기관회원용) 추가 필요
}
