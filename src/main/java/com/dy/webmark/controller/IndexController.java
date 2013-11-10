package com.dy.webmark.controller;

import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duanbn.validation.Rule;
import com.duanbn.validation.RuleBuilder;
import com.duanbn.validation.Validate;
import com.duanbn.validation.exception.CheckFailureException;
import com.duanbn.validation.validator.impl.StringValidator;
import com.dy.webmark.common.ErrorCode;
import com.dy.webmark.common.WebConst;
import com.dy.webmark.entity.User;
import com.dy.webmark.exception.BizException;
import com.dy.webmark.service.IUserLoginService;
import com.dy.webmark.service.IUserService;

@Controller
public class IndexController extends BaseController {

    public static final Logger LOG = Logger.getLogger(UserController.class);

    @RequestMapping("/")
    public String login() {
        return "login";
    }

}
