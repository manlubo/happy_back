package com.gitbaby.happy_back.domain.member.entity;

import com.gitbaby.happy_back.domain.common.entity.BaseCreatedEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "deleted_member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeletedMember extends BaseCreatedEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "deleted_member_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @Column(nullable = false)
  private String email;
}
