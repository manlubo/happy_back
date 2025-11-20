package com.gitbaby.happy_back.domain.member.repository;

import com.gitbaby.happy_back.domain.member.en.ProviderName;
import com.gitbaby.happy_back.domain.member.entity.MemberProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberProviderRepository extends JpaRepository<MemberProvider, Long> {
  Optional<MemberProvider> findByProviderNameAndProviderUuid(ProviderName providerName, String providerUuid);
}
