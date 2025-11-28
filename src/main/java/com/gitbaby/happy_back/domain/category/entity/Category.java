package com.gitbaby.happy_back.domain.category.entity;

import com.gitbaby.happy_back.domain.common.entity.BaseTimeEntity;
import com.gitbaby.happy_back.domain.category.en.CategoryType;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class Category extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_id")
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private CategoryType type;

  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "parent_category_id", nullable = true)
  private Category parentCategory;

  @Column(nullable = false)
  private int sortOrder;

  @Column(nullable = false)
  private boolean visible;
}
