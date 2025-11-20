package com.gitbaby.happy_back.domain.member.repository;

import com.gitbaby.happy_back.domain.member.entity.MemberLoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLoginHistoryRepository extends JpaRepository<MemberLoginHistory, Long> {
}
