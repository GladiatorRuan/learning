package SpringBoot.learning.validation;

import SpringBoot.learning.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State,String> {
//    @Override
//    public void initialize(State constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
//    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        }
        if (value.equals("已发布")|| value.equals("草稿")) {
            return true;
        }
        return false;
    }
}
