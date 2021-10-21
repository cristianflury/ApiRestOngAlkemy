package org.alkemy.somosmas.repository;

import org.alkemy.somosmas.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String roleName);

    boolean existsByName(String roleName);

}
