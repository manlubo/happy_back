package com.gitbaby.happy_back.domain.board.entity;

import com.gitbaby.happy_back.domain.common.entity.BaseTimeEntity;
import com.gitbaby.happy_back.domain.board.en.BoardStatus;
import com.gitbaby.happy_back.domain.category.entity.Category;
import com.gitbaby.happy_back.domain.member.entity.Member;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "board")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class Board extends BaseTimeEntity {
  @Id
  @Column(name = "board_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String content;

  @Column(nullable = true)
  private String thumbnailUrl;

  @Column(nullable = false)
  @Builder.Default
  private int viewCount = 0;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private BoardStatus status;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;
}
