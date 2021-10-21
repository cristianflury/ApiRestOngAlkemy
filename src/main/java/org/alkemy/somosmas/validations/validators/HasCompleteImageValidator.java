package org.alkemy.somosmas.validations.validators;

import org.alkemy.somosmas.dto.UpdateCategoryDto;
import org.alkemy.somosmas.validations.annotation.HasCompleteImage;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HasCompleteImageValidator implements ConstraintValidator<HasCompleteImage, UpdateCategoryDto> {

    @Override
    public boolean isValid(UpdateCategoryDto updateCategoryDto, ConstraintValidatorContext context) {

        if (updateCategoryDto.getImage() == null || updateCategoryDto.getImage().isBlank()) {

            return true;

        } else {

            return updateCategoryDto.getFileType() != null && !updateCategoryDto.getFileType().isBlank();

        }

    }
}
