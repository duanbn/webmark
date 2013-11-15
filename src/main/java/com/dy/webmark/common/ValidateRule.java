package com.dy.webmark.common;

import com.duanbn.validation.Opt;
import com.duanbn.validation.Rule;
import com.duanbn.validation.RuleBuilder;
import com.duanbn.validation.validator.impl.EmailValidator;
import com.duanbn.validation.validator.impl.NumberValidator;
import com.duanbn.validation.validator.impl.StringValidator;

public class ValidateRule {

    /**
     * 用户id校验
     */
    public static final Rule userIdRule;

    public static final Rule emailRule;

    public static final Rule clipNameRule;

    static {
        userIdRule = RuleBuilder.build().addValidator(NumberValidator.class).length(0, Opt.GT);
        emailRule = RuleBuilder.build().addValidator(EmailValidator.class).isNull(false);

        clipNameRule = RuleBuilder.build().addValidator(StringValidator.class).isNull(false);
    }

}
