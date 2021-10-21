package org.alkemy.somosmas.controller;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.dto.TestimonialRequestDto;
import org.alkemy.somosmas.dto.TestimonialResponseDto;
import org.alkemy.somosmas.mapper.TestimonialMapper;
import org.alkemy.somosmas.service.TestimonialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/testimonials")
@RequiredArgsConstructor
public class TestimonialController {
    private final TestimonialMapper testimonialMapper;
    private final TestimonialService testimonialService;
    @PostMapping(path = "/")
    public TestimonialResponseDto createTestimonial(@Valid @RequestBody TestimonialRequestDto testimonialRequestDto) throws Exception {
                return testimonialMapper.toDto(
                        testimonialService.create(testimonialMapper.toEntity(testimonialRequestDto), testimonialRequestDto.getImageFileType()));
    }
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTestimonial(@PathVariable Long id) throws Exception {
        testimonialService.delete(id);
    }
    @PutMapping("/{id}")
    public TestimonialResponseDto updateTestimonial(@Valid @RequestBody TestimonialRequestDto testimonialBody, @PathVariable Long id) throws Exception {
        return testimonialMapper.toDto(testimonialService.update(id,testimonialMapper.toEntity(testimonialBody), testimonialBody.getImageFileType()));
    }
}
