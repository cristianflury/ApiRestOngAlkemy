package org.alkemy.somosmas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CategoryDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "El nombre de la categoría no debe estar vacío")
    @Size(max = 255)
    @Pattern(regexp = "^[a-zA-Z]*$", message = "No debe contener espacios, números o caracteres especiales -"  )
    private String name;

    @NotBlank(message = "La descripción de la categoría no debe estar vacía")
    private String description;

    @NotBlank(message = "La imagen de la categoría no debe estar vacía")
    private String image;

    @NotBlank(message = "El tipo de imagen de la categoría no debe estar vacío")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String fileType;

}
