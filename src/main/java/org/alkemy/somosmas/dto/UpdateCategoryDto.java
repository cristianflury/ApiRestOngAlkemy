package org.alkemy.somosmas.dto;

import lombok.Getter;
import lombok.Setter;
import org.alkemy.somosmas.validations.annotation.HasCompleteImage;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@HasCompleteImage
public class UpdateCategoryDto {

    @NotBlank(message = "El nombre de la categoría no debe estar vacío")
    @Size(max = 255)
    @Pattern(regexp = "^[a-zA-Z]*$", message = "No debe contener espacios, números o caracteres especiales")
    private String name;

    private String description;

    private String image;

    private String fileType;

}
