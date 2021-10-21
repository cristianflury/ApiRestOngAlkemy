package org.alkemy.somosmas.repository;

import org.alkemy.somosmas.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Optional <Organization> findByIdAndDeletedFalse(Long id);

    boolean existsByName(String organizationName);

    Organization findByName(String organizationName);
}
