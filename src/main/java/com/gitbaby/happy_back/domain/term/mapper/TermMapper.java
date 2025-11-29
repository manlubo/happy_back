package com.gitbaby.happy_back.domain.term.mapper;

import org.mapstruct.Mapper;

import com.gitbaby.happy_back.domain.term.dto.TermCreateRequest;
import com.gitbaby.happy_back.domain.term.entity.Term;

@Mapper(componentModel = "spring")
public interface TermMapper {
  Term toEntity(TermCreateRequest termCreateRequest);
}
