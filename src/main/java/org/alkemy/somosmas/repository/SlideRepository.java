package org.alkemy.somosmas.repository;

import org.alkemy.somosmas.model.Slide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SlideRepository extends JpaRepository<Slide, Long> {

    Optional<Slide> findTop1ByOrganizationIdOrderByOrderDesc(Long organizationId);

    Optional<Slide> findByIdAndOrganizationId(Long id, Long id1);

    List<Slide> findByOrganizationId(Long id);
}
