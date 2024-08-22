package com.arms.util.validation.validator;

import com.arms.util.cron.MadCronExpression;
import com.arms.util.validation.CronCheck;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CronCheckValidator implements ConstraintValidator<CronCheck, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!ObjectUtils.isEmpty(value)) {
            MadCronExpression madCronExpression = new MadCronExpression(value);
            if(madCronExpression.isLessThan600seconds()){
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("10분이내의 주기가 포함되는 표현식은 저장 하실수 없습니다").addConstraintViolation();
                return false;
            }

        }
        return true;
    }


}
