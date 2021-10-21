package org.alkemy.somosmas.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SlideRequestDto extends BaseSlideDto {
    @NotBlank
    private String imageBase64;
    @NotNull
    private String fileType;
}
