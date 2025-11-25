package com.gitbaby.happy_back.security.service;

import com.gitbaby.happy_back.domain.member.en.ProviderName;
import com.gitbaby.happy_back.domain.member.entity.Member;
import com.gitbaby.happy_back.domain.member.entity.MemberProvider;
import com.gitbaby.happy_back.domain.member.mapper.MemberMapper;
import com.gitbaby.happy_back.domain.member.repository.MemberProviderRepository;
import com.gitbaby.happy_back.domain.member.repository.MemberRepository;
import com.gitbaby.happy_back.security.dto.MemberAuthDTO;
import com.gitbaby.happy_back.security.dto.SocialProcessDTO;
import com.gitbaby.happy_back.security.dto.SocialResult;
import com.gitbaby.happy_back.security.en.SocialProcessType;
import com.gitbaby.happy_back.security.recode.SocialUser;
import com.gitbaby.happy_back.security.util.SocialUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Log4j2
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

  private final SocialUtil socialUtil;
  private final MemberProviderRepository memberProviderRepository;
  private final MemberMapper memberMapper;
  private final MemberRepository memberRepository;

  @Override
  @Transactional
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);

    ProviderName providerName =
      ProviderName.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());

    SocialUser socialUser = socialUtil.getSocialUser(providerName, oAuth2User);

    // provider로 소셜 계정 찾기
    Optional<MemberProvider> providerOpt =
      memberProviderRepository.findByProviderNameAndProviderUuid(
        socialUser.providerName(),
        socialUser.providerUuid()
      );

    // 가입된 계정 > 로그인
    if (providerOpt.isPresent()) {
      Member member = providerOpt.get().getMember();
      MemberAuthDTO authDTO = memberMapper.toMemberAuthDTO(member);
      return new SocialResult(authDTO);
    }

    // 가입 안된 계정 > 이메일로 유저 유무 확인
    Optional<Member> originMember = memberRepository.findByEmail(socialUser.email());

    if (originMember.isPresent()) {
      // 기존 멤버 있음 > 가입불가
      SocialProcessDTO errorDto = SocialProcessDTO.builder()
        .socialUser(socialUser)
        .type(SocialProcessType.FORBIDDEN_EMAIL)
        .build();

      return new SocialResult(errorDto);
    } else {
      // 기존 멤버 없음 > 소셜 신규 가입 필요
      SocialProcessDTO signupDto = SocialProcessDTO.builder()
        .socialUser(socialUser)
        .type(SocialProcessType.SIGNUP)
        .build();

      return new SocialResult(signupDto);
    }
  }
}
