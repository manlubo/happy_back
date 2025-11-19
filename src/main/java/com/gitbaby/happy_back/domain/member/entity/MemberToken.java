package com.gitbaby.happy_back.domain.member.entity;

import com.gitbaby.happy_back.domain.common.entity.BaseCreatedEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "member_token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberToken extends BaseCreatedEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_token_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "member_id", nullable = false, unique = true)
  private Member member;

  @Column(nullable = false, unique = true)
  private String token;

  @Column(nullable = false)
  private LocalDateTime expiredAt;
}
