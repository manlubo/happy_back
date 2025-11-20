package com.gitbaby.happy_back.domain.member.repository;

import com.gitbaby.happy_back.domain.member.entity.MemberToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTokenRepository extends JpaRepository<MemberToken, Long> {
}
