package org.alkemy.somosmas.repository;

import org.alkemy.somosmas.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
