package com.gitbaby.happy_back.domain.member.mapper;

import com.gitbaby.happy_back.domain.member.dto.MemberLoginResponse;
import com.gitbaby.happy_back.domain.member.dto.MemberSignupRequest;
import com.gitbaby.happy_back.domain.member.en.Role;
import com.gitbaby.happy_back.domain.member.en.SignupRole;
import com.gitbaby.happy_back.domain.member.entity.Member;
import com.gitbaby.happy_back.security.dto.MemberAuthDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface MemberMapper {

  default Set<Role> mapRoles(SignupRole role) {
    Set<Role> roles = new HashSet<>();

    roles.add(Role.USER);

    if(SignupRole.ORG.equals(role)){
      roles.add(Role.ORG);
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

  MemberLoginResponse toMemberLoginResponse(Member entity);
}
