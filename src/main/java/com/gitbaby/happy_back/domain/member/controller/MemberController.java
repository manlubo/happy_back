package com.gitbaby.happy_back.domain.member.controller;

import com.gitbaby.happy_back.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/member")
@Log4j2
public class MemberController {
  private final MemberService memberService;
}
