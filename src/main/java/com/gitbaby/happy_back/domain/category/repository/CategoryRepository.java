package com.gitbaby.happy_back.domain.category.repository;

import com.gitbaby.happy_back.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
