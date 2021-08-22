package com.zlab.commune.api.member;

import com.zlab.commune.api.member.validation.ReferralCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ReferralCodeConstraintValidator implements ConstraintValidator<ReferralCode, String> {

    private String[] referralPrefixes;

    @Override
    public void initialize(ReferralCode referralCode) {

        referralPrefixes = referralCode.value();

    }

    @Override
    public boolean isValid(String referralCode, ConstraintValidatorContext constraintValidatorContext) {

        boolean result = false;

        if(referralCode != null){

            for(String prefix : referralPrefixes) {

                result = referralCode.startsWith(prefix);

                if(result) {break;}
            }

        }else{
            result = true;
        }

        return result;
    }

}
