package com.dy.webmark.common;

import com.duanbn.validation.Opt;
import com.duanbn.validation.Rule;
import com.duanbn.validation.RuleBuilder;
import com.duanbn.validation.validator.impl.EmailValidator;
import com.duanbn.validation.validator.impl.NumberValidator;

public class ValidateRule {

    /**
     * 用户id校验
     */
    public static final Rule userIdRule;

    public static final Rule emailRule;

    static {
        userIdRule = RuleBuilder.build().addValidator(NumberValidator.class).length(0, Opt.GT);
        emailRule = RuleBuilder.build().addValidator(EmailValidator.class).isNull(false);
    }

}
