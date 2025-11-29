package com.gitbaby.happy_back.domain.donation.entity;

import java.time.LocalDateTime;

import com.gitbaby.happy_back.domain.common.entity.BaseCreatedEntity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "donation_round")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class DonationRound extends BaseCreatedEntity{
  @Id
  @Column(name = "donation_round_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "donation_id", nullable = false)
  private Donation donation;

  @Column(nullable = false)
  private Integer round;
  
  @Column(nullable = false)
  @Builder.Default
  private long currentAmount = 0;

  @Column(nullable = false)
  private long targetAmount;

  @Column(nullable = true)
  private LocalDateTime expiredAt;
}
