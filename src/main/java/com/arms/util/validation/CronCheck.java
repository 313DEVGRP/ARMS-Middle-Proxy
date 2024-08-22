package com.arms.util.validation;


import com.arms.util.validation.validator.CronCheckValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CronCheckValidator.class)
public @interface CronCheck {

    String message() default "설정을 확인 하시길 바랍니다.";

    String[] values() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
