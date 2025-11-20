package com.gitbaby.happy_back.security.util;

import com.gitbaby.happy_back.domain.member.en.ProviderName;
import com.gitbaby.happy_back.security.recode.SocialUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SocialUtil {
  public SocialUser getSocialUser(ProviderName providerName, OAuth2User oAuth2User) {
    SocialUser socialUser = null;
    switch(providerName) {
      case GOOGLE -> socialUser = getGoogleUser(providerName, oAuth2User);
      case NAVER ->  socialUser = getNaverUser(providerName, oAuth2User);
      case KAKAO ->  socialUser = getKakaoUser(providerName, oAuth2User);
    }
    return socialUser;
  }

  private SocialUser getGoogleUser(ProviderName providerName, OAuth2User oAuth2User) {
    return new SocialUser(
      providerName,
      oAuth2User.getAttribute("sub"),
      oAuth2User.getAttribute("email"),
      oAuth2User.getAttribute("name")
    );
  }

  private SocialUser getNaverUser(ProviderName providerName, OAuth2User oAuth2User) {
    Map<String, Object> response = (Map<String, Object>) oAuth2User.getAttributes().get("response");

    return new SocialUser(
      providerName,
      (String) response.get("id"),
      (String) response.get("email"),
      (String) response.get("name")
    );
  }

  private SocialUser getKakaoUser(ProviderName providerName, OAuth2User oAuth2User) {
    Map<String, Object> attributes = oAuth2User.getAttributes();

    // id → providerUid
    String providerUid = String.valueOf(attributes.get("id"));

    // nickname → kakao_account.profile.nickname 기준 (properties.nickname도 가능)
    Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
    Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
    String nickname = (String) profile.get("nickname");
    String email = (String) kakaoAccount.get("email");


    return new SocialUser(providerName, providerUid, email, nickname);
  }
}

