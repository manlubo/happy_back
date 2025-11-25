package com.gitbaby.happy_back.security.dto;

import com.gitbaby.happy_back.security.en.SocialProcessType;
import com.gitbaby.happy_back.security.recode.SocialUser;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SocialProcessDTO {
  private SocialProcessType type;
  private SocialUser socialUser;
}
