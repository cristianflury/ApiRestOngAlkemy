package org.alkemy.somosmas.mapper;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.dto.MemberDto;
import org.alkemy.somosmas.model.Member;
import org.alkemy.somosmas.repository.OrganizationRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberMapper {

    public MemberDto toDto(Member member){
        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setName(member.getName());
        memberDto.setImage(member.getImage());
        memberDto.setDescription(member.getDescription());
        memberDto.setFacebookUrl(member.getFacebookUrl());
        memberDto.setInstagramUrl(member.getInstagramUrl());
        memberDto.setLinkedinUrl(member.getInstagramUrl());
        return memberDto;
    }

    public Member toModel(MemberDto memberDto){
        Member member = new Member();
        member.setId(memberDto.getId());
        member.setName(memberDto.getName());
        member.setImage(memberDto.getImage());
        member.setDescription(memberDto.getDescription());
        member.setFacebookUrl(memberDto.getFacebookUrl());
        member.setInstagramUrl(memberDto.getInstagramUrl());
        member.setLinkedinUrl(memberDto.getLinkedinUrl());
        return member;
    }


}
