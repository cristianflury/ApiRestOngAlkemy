package org.alkemy.somosmas.service.impl;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.model.Testimonial;
import org.alkemy.somosmas.repository.TestimonialRepository;
import org.alkemy.somosmas.service.ImageHandlerService;
import org.alkemy.somosmas.service.TestimonialService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TestimonialServiceImpl implements TestimonialService {
    private final TestimonialRepository testimonialRepository;
    private final ImageHandlerService imageHandlerService;
    @Override
    @PreAuthorize("@authorizationService.loggedInUserHasAdminRole()")
    public Testimonial create(Testimonial testimonial, String fileType) throws Exception {
        testimonial.setImage(imageHandlerService.decodeImageAndCreateUrl(testimonial.getImage(), fileType));
        testimonialRepository.save(testimonial);
        return testimonial;
    }

    @Override
    @PreAuthorize("@authorizationService.loggedInUserHasAdminRole()")
    public void delete(long id) throws Exception {
        Testimonial testimonial = testimonialRepository.findById(id).orElseThrow();
        testimonialRepository.delete(testimonial);
        imageHandlerService.deleteFileFromS3Bucket(testimonial.getImage());
    }

    @Override
    @PreAuthorize("@authorizationService.loggedInUserHasAdminRole()")
    public Testimonial update(long id, Testimonial newTestimonialInfo, String fileType) throws Exception {
        Testimonial formerTestimonial = testimonialRepository.findById(id).orElseThrow();
        Testimonial updatedTestimonial = updateFields(formerTestimonial,newTestimonialInfo,fileType);
        testimonialRepository.save(updatedTestimonial);
        return updatedTestimonial;
    }

    private Testimonial updateFields(Testimonial formerTestimonial, Testimonial newTestimonial, String fileType) throws Exception {
        formerTestimonial.setContent(newTestimonial.getContent());
        formerTestimonial.setName(newTestimonial.getName());
        imageHandlerService.deleteFileFromS3Bucket(formerTestimonial.getImage());
        formerTestimonial.setImage(imageHandlerService.decodeImageAndCreateUrl(newTestimonial.getImage(), fileType));
        return formerTestimonial;
    }
}
