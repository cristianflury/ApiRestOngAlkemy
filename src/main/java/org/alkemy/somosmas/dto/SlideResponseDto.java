package org.alkemy.somosmas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlideResponseDto extends BaseSlideDto {
    private Long id;
    private String imageUrl;
}
