package org.example.internetshopspring.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LastNameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface LastNameConstraint {
    String message() default "Invalid last name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
