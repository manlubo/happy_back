package com.gitbaby.happy_back.domain.term.entity;

import com.gitbaby.happy_back.domain.common.entity.BaseCreatedEntity;
import com.gitbaby.happy_back.domain.term.en.TermType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "term")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Term extends BaseCreatedEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "term_id")
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TermType type;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private String version;

  @Column(nullable = false)
  private Boolean required;

  @Column(nullable = false)
  @Builder.Default
  private Boolean active = false;
}
