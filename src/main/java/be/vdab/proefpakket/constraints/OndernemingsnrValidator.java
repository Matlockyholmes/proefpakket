package be.vdab.proefpakket.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OndernemingsnrValidator implements ConstraintValidator<Ondernemingsnr, Long> {
    @Override
    public void initialize(Ondernemingsnr constraintAnnotation) {

    }

    @Override
    public boolean isValid(Long ondernemingsnr, ConstraintValidatorContext context) {
        long ondernemingsnrRest = ondernemingsnr % 100;
        long resterendOndernemingsnr = ondernemingsnr / 100;
        return ondernemingsnrRest == 97 - (resterendOndernemingsnr % 97);
    }
}
