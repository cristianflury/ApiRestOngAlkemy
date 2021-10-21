package org.alkemy.somosmas.controller;


import org.alkemy.somosmas.SomosMasUnitTests;
import org.alkemy.somosmas.model.Testimonial;
import org.alkemy.somosmas.repository.TestimonialRepository;
import org.alkemy.somosmas.service.impl.ImageHandlerServiceImpl;
import org.alkemy.somosmas.service.impl.TestimonialServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class TestimonialServiceUnitTests extends SomosMasUnitTests {
    @InjectMocks
    TestimonialServiceImpl testimonialService;
    @Mock
    TestimonialRepository testimonialRepository;
    @Mock
    ImageHandlerServiceImpl imageHandlerService;

    @Test
    void delete_testimonial_works() throws Exception {
        Testimonial testimonial = new Testimonial();
        testimonial.setImage("https://alkemy-ong.s3.amazonaws.com/2021-09-28T21:21:30.897470_image_user8b6b7de5-423d-4c3e-9e84-fbf4a33d87b5.jpg");
        when(testimonialRepository.findById(5L))
                .thenReturn(java.util.Optional.of(testimonial));

        testimonialService.delete(5L);

        verify(testimonialRepository,times(1)).delete(any());
        verify(imageHandlerService,times(1)).deleteFileFromS3Bucket(any());
    }
    @Test
    void delete_testimonial_that_does_not_exist_throws_exception() throws Exception {
        assertThrows(NoSuchElementException.class,() -> {
            testimonialService.delete(5L);
        });

        verify(testimonialRepository,never()).delete(any());
        verify(imageHandlerService,never()).deleteFileFromS3Bucket(any());
    }
    @Test
    void update_testimonial_works() throws Exception {
        Testimonial oldTestimonial = new Testimonial();
        oldTestimonial.setId(1L);
        when(testimonialRepository.findById(1L))
                .thenReturn(java.util.Optional.of(oldTestimonial));
        Testimonial newTestimonial = new Testimonial();
        newTestimonial.setImage("asdasdaaascasdafasf");
        newTestimonial.setName("new name");
        newTestimonial.setContent("new content");
        Testimonial updatedTestimonial = testimonialService.update(1L,newTestimonial,"jpg");

        assertEquals(updatedTestimonial.getName(),"new name");
        assertEquals(updatedTestimonial.getContent(),"new content");
        assertEquals(updatedTestimonial.getId(),1L);

        verify(testimonialRepository,times(1)).save(any());
        verify(imageHandlerService,times(1)).deleteFileFromS3Bucket(any());
        verify(imageHandlerService,times(1)).decodeImageAndCreateUrl(any(),any());
    }
    @Test
    void update_testimonial_that_does_not_exist_throws_exception() {
        assertThrows(NoSuchElementException.class,() -> {
            testimonialService.update(5L, new Testimonial(),".jpg");
        });

        verify(testimonialRepository,never()).save(any());
    }
}
