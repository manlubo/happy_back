package com.gitbaby.happy_back.domain.payment.entity;

import com.gitbaby.happy_back.domain.common.entity.BaseTimeEntity;
import com.gitbaby.happy_back.domain.member.entity.Member;
import com.gitbaby.happy_back.domain.payment.en.PaymentMethod;
import com.gitbaby.happy_back.domain.payment.en.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class Payment extends BaseTimeEntity {
  @Id
  @Column(name = "payment_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long amount;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @Column(unique = true)
  private String impUuid;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private PaymentMethod method;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private PaymentStatus status;
}