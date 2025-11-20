package com.gitbaby.happy_back.domain.member.repository;

import com.gitbaby.happy_back.domain.member.entity.DeletedMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeletedMemberRepository extends JpaRepository<DeletedMember, Long> {
}
