package pl.projects.register.register.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CustomerIsAdultValidator implements ConstraintValidator<CustomerIsAdult, LocalDate> {

    public static final int ADULT_AGE = 18;

    @Override
    public boolean isValid(LocalDate birthday, ConstraintValidatorContext constraintValidatorContext) {
        return birthday == null ? true: isAdult(birthday);

    }

    private boolean isAdult(LocalDate birthday) {
        return ADULT_AGE <= LocalDate.from(birthday).until(LocalDate.now(), ChronoUnit.YEARS);
    }
}
