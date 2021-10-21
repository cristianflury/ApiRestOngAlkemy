package org.alkemy.somosmas.controller;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.dto.MemberDto;
import org.alkemy.somosmas.mapper.MemberMapper;
import org.alkemy.somosmas.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService membersService;
    private final MemberMapper membersMapper;

    @PostMapping
    public MemberDto registerActivity(@Valid @RequestBody MemberDto membersDto){
        return membersMapper.toDto(membersService.create(membersMapper.toModel(membersDto)));
    }
}
