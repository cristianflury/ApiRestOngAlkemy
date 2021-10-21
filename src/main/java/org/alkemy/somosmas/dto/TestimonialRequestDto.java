package org.alkemy.somosmas.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TestimonialRequestDto extends BaseTestimonialDto {
    @NotBlank
    private String base64image;
    @NotBlank
    private String imageFileType;
}
