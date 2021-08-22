package com.zlab.commune.api.member.validation;


import com.zlab.commune.api.member.ReferralCodeConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = ReferralCodeConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReferralCode {

    //define default referral code
    public String[] value() default {"REF"};

    //define default error message
    public String message() default "must start with REF";

    //define default groups
    public Class<?>[] groups() default {};

    //define default payloads
    public Class<? extends Payload>[] payload() default {};

}
