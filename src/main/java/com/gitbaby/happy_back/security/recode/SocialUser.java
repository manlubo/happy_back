package com.gitbaby.happy_back.security.recode;

import com.gitbaby.happy_back.domain.member.en.ProviderName;

public record SocialUser(
  ProviderName providerName,
  String providerUuid,
  String email,
  String name
) {}
