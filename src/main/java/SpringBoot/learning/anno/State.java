package SpringBoot.learning.anno;

import SpringBoot.learning.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented //元注解
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {StateValidation.class}) //指定提供校验规则的类
public @interface State {
    String message() default "state参数的值只能是已发布或者草稿";

    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}

