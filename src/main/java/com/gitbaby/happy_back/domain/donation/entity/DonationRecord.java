package com.gitbaby.happy_back.domain.donation.entity;

import com.gitbaby.happy_back.domain.common.entity.BaseCreatedEntity;
import com.gitbaby.happy_back.domain.member.entity.Member;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "donation_record")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class DonationRecord extends BaseCreatedEntity{
  @Id
  @Column(name = "donation_record_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "donation_round_id", nullable = false)
  private DonationRound donationRound;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @Column(nullable = false)
  private Long amount;
}
