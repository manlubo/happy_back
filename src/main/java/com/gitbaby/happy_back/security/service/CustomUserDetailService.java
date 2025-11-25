package com.gitbaby.happy_back.security.service;

import com.gitbaby.happy_back.domain.member.entity.Member;
import com.gitbaby.happy_back.domain.member.mapper.MemberMapper;
import com.gitbaby.happy_back.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
  private final MemberRepository memberRepository;
  private final MemberMapper memberMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Member member = memberRepository.findById(Long.valueOf(username))
      .orElse(null);
    return memberMapper.toMemberAuthDTO(member);
  }
}
