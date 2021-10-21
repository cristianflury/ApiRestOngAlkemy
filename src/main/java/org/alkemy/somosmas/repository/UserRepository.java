package org.alkemy.somosmas.repository;

import org.alkemy.somosmas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByDeletedFalse();
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
    Optional<User> findByIdAndOrganizationId(Long userId,Long OrganizationId);

}
