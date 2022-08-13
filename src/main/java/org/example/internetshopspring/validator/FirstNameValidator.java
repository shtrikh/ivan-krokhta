package org.example.internetshopspring.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FirstNameValidator implements ConstraintValidator<FirstNameConstraint, String> {

    @Override
    public void initialize(FirstNameConstraint size) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        return contactField != null && contactField.matches("[A-Z][a-z]*")
                && (contactField.length() > 1) && (contactField.length() < 25);
    }
}
