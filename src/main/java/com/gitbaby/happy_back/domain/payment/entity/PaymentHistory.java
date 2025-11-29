package com.gitbaby.happy_back.domain.payment.entity;

import com.gitbaby.happy_back.domain.common.entity.BaseCreatedEntity;
import com.gitbaby.happy_back.domain.payment.en.PaymentStatus;
import com.gitbaby.happy_back.domain.payment.en.PaymentType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payment_history")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class PaymentHistory extends BaseCreatedEntity {
  @Id
  @Column(name = "payment_history_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long amount;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "payment_id", nullable = false)
  private Payment payment;

  @Column(unique = true)
  private String receiptUrl;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private PaymentType type;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private PaymentStatus status;
}