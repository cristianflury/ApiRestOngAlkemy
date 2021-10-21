package org.alkemy.somosmas.repository;

import org.alkemy.somosmas.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {

    boolean existsById(Long id);

    Optional<News> findByIdAndDeletedTrue(Long id);

    News findByIdAndDeletedFalse(Long id);

    Page<News> findAllByOrganizationId(Pageable pageable, Long id );

}