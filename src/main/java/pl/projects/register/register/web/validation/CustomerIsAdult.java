package pl.projects.register.register.web.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomerIsAdultValidator.class)
public @interface CustomerIsAdult {
    String message() default "Customer is too young";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
