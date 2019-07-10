package be.vdab.proefpakket.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OndernemingsnrValidator.class)
public @interface Ondernemingsnr {
    String message() default "{be.vdab.proefpakket.constraints.Ondernemingsnr.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
