package org.alkemy.somosmas.validations.annotation;

import org.alkemy.somosmas.validations.validators.HasCompleteImageValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = HasCompleteImageValidator.class)
public @interface HasCompleteImage {

    String message() default "Si quieres cargar una imagen, debes especificar su tipo";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
