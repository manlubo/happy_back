package com.gitbaby.happy_back.domain.reaction.entity;

import com.gitbaby.happy_back.domain.board.entity.Board;
import com.gitbaby.happy_back.domain.common.entity.BaseCreatedEntity;
import com.gitbaby.happy_back.domain.member.entity.Member;
import com.gitbaby.happy_back.domain.reply.entity.Reply;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "reaction")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class Reaction extends BaseCreatedEntity{
  @Id
  @Column(name = "reaction_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "board_id", nullable = true)
  private Board board;

  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "reply_id", nullable = true)
  private Reply reply;
}
