package com.gitbaby.happy_back.domain.member.repository;

import com.gitbaby.happy_back.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
  Optional<Member> findByEmail(String email);

  boolean existsByEmail(String email);

  Optional<Member> findByTel(String tel);

  boolean existsByTel(String tel);

  @Modifying(clearAutomatically = true)
  @Query("update Member m set m.tel = null where m.tel = :tel")
  void clearTel(@Param("tel") String tel);
}
