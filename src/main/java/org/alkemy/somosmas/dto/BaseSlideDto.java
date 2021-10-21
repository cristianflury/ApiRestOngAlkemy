package org.alkemy.somosmas.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public abstract class BaseSlideDto {
    @NotBlank
    private String text;
    private Integer order;
}
