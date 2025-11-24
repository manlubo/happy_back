package com.gitbaby.happy_back.domain.member.mapper;

import com.gitbaby.happy_back.domain.member.dto.MemberSignupRequest;
import com.gitbaby.happy_back.domain.member.en.Role;
import com.gitbaby.happy_back.domain.member.entity.Member;
import com.gitbaby.happy_back.security.dto.MemberAuthDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface MemberMapper {

  default Set<Role> mapRoles(Role role) {
    Set<Role> roles = new HashSet<>();

    roles.add(Role.USER);

    switch (role) {
      case ORG -> roles.add(Role.ORG);
      case ADMIN -> {
        roles.add(Role.ORG);
        roles.add(Role.ADMIN);
      }
    }

    return roles;
  }

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "roles", expression = "java(mapRoles(memberSignupRequest.getRole()))")
  Member toEntity(MemberSignupRequest memberSignupRequest);


  @Mapping(source = "name", target = "realName")
  @Mapping(target = "attributes", ignore = true)
  MemberAuthDTO toMemberAuthDTO(Member entity);
}
