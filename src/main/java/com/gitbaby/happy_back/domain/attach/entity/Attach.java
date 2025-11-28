package com.gitbaby.happy_back.domain.attach.entity;

import com.gitbaby.happy_back.domain.common.entity.BaseCreatedEntity;
import com.gitbaby.happy_back.domain.member.entity.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "attach")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class Attach extends BaseCreatedEntity {
  @Id
  @Column(name = "attach_uuid")
  private Long uuid;

  @Column
  private String path;

  @Column
  private Boolean image;

  @Column
  private String origin;

  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "member_id", nullable = true)
  private Member member;

  // 게시글 추가
  // @ManyToOne(fetch = FetchType.LAZY, optional = true)
  // @JoinColumn(name = "board_id", nullable = true)
  // private Board board;
}
