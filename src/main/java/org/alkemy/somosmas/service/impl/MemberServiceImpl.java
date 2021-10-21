package org.alkemy.somosmas.service.impl;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.model.Member;
import org.alkemy.somosmas.model.User;
import org.alkemy.somosmas.repository.MemberRepository;
import org.alkemy.somosmas.repository.UserRepository;
import org.alkemy.somosmas.service.MemberService;
import org.alkemy.somosmas.util.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final UserRepository userRepository;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Member create(Member member) {
        User user = userRepository.findByEmail(SecurityUtils.getLoggedInUser().getUsername()).get();
        member.setOrganization(user.getOrganization());
        return memberRepository.save(member);
    }
}
