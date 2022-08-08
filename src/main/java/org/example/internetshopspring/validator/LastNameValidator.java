package org.example.internetshopspring.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LastNameValidator implements ConstraintValidator<LastNameConstraint, String> {

    @Override
    public void initialize(LastNameConstraint size) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        return contactField != null && contactField.matches("[A-Z]+([ '-][a-zA-Z]+)*")
                && (contactField.length() > 1) && (contactField.length() < 25);
    }
}
