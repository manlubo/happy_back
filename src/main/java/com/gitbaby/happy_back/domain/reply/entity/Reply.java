package com.gitbaby.happy_back.domain.reply.entity;

import com.gitbaby.happy_back.domain.board.entity.Board;
import com.gitbaby.happy_back.domain.common.entity.BaseTimeEntity;
import com.gitbaby.happy_back.domain.member.entity.Member;

import jakarta.persistence.*;
import lombok.*;

@Entity
public class Reply extends BaseTimeEntity {
  @Id
  @Column(name = "reply_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String content;

  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "parent_reply_id", nullable = true)
  private Reply parentReply;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "board_id", nullable = false)
  private Board board;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;
}