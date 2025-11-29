package com.gitbaby.happy_back.domain.term.mapper;

import com.gitbaby.happy_back.domain.term.dto.TermCreateResponse;
import org.mapstruct.Mapper;

import com.gitbaby.happy_back.domain.term.dto.TermCreateRequest;
import com.gitbaby.happy_back.domain.term.entity.Term;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TermMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "version", ignore = true)
  @Mapping(target = "active", ignore = true)
  Term toEntity(TermCreateRequest termCreateRequest);
  TermCreateResponse toCreateResponse(Term term);
}
