package com.gitbaby.happy_back.domain.member.entity;

import com.gitbaby.happy_back.domain.common.entity.BaseTimeEntity;
import com.gitbaby.happy_back.domain.member.en.ProviderName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member_provider")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class MemberProvider extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_provider_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ProviderName providerName;

  @Column(nullable = false, unique = true)
  private String providerUuid;

  @Column(nullable = false)
  private String email;
}
