package com.gitbaby.happy_back.domain.member.entity;

import com.gitbaby.happy_back.domain.common.entity.BaseTimeEntity;
import com.gitbaby.happy_back.domain.member.en.MemberStatus;
import com.gitbaby.happy_back.domain.member.en.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class Member extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id")
  private Long id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column
  private String password;

  @Column(unique = true)
  private String tel;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String address;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private MemberStatus status;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(
    name = "member_role",
    joinColumns = @JoinColumn(name = "member_id")
  )
  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = false)
  @Builder.Default
  private Set<Role> roles = new HashSet<>(Set.of(Role.USER));
}
