package org.alkemy.somosmas.service;

import org.alkemy.somosmas.model.Testimonial;

public interface TestimonialService {
    Testimonial create(Testimonial testimonial, String fileType) throws Exception;

    void delete(long id) throws Exception;

    Testimonial update(long id, Testimonial newTestimonialInfo, String fileType) throws Exception;
}
