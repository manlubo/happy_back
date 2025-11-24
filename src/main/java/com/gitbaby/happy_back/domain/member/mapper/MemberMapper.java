package com.gitbaby.happy_back.domain.member.mapper;

import com.gitbaby.happy_back.domain.member.dto.MemberSignupRequest;
import com.gitbaby.happy_back.domain.member.entity.Member;
import com.gitbaby.happy_back.security.dto.MemberAuthDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "status", ignore = true)
  Member toEntity(MemberSignupRequest memberSignupRequest);


  @Mapping(source = "name", target = "realName")
  @Mapping(target = "attributes", ignore = true)
  MemberAuthDTO toMemberAuthDTO(Member entity);
}
