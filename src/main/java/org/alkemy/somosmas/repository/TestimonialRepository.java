package org.alkemy.somosmas.repository;

import org.alkemy.somosmas.model.Testimonial;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface TestimonialRepository extends JpaRepository<Testimonial, Long> {
    }
