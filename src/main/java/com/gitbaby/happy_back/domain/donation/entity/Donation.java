package com.gitbaby.happy_back.domain.donation.entity;

import com.gitbaby.happy_back.domain.board.entity.Board;
import com.gitbaby.happy_back.domain.common.entity.BaseTimeEntity;
import com.gitbaby.happy_back.domain.donation.en.DonationStatus;
import com.gitbaby.happy_back.domain.member.entity.Member;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "donation")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class Donation extends BaseTimeEntity{
  @Id
  @Column(name = "donation_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "board_id", nullable = false)
  private Board board;

  @Column
  @Enumerated(EnumType.STRING)
  private DonationStatus status;
}
