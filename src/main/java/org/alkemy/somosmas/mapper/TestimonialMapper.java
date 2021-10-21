package org.alkemy.somosmas.mapper;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.dto.TestimonialRequestDto;
import org.alkemy.somosmas.dto.TestimonialResponseDto;
import org.alkemy.somosmas.model.Testimonial;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestimonialMapper {

    public Testimonial toEntity(TestimonialRequestDto dto) {
        Testimonial testimonial = new Testimonial();
        testimonial.setName(dto.getName());
        testimonial.setContent(dto.getContent());
        testimonial.setImage(dto.getBase64image());
        return testimonial;
    }
    public TestimonialResponseDto toDto(Testimonial model) {
        TestimonialResponseDto dto = new TestimonialResponseDto();
        dto.setId(model.getId());
        dto.setContent(model.getContent());
        dto.setName(model.getName());
        dto.setImageUrl(model.getImage());
        return dto;
    }
}
