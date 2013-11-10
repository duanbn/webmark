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
@RequestMapping("/user")
public class UserController extends BaseController {

    public static final Logger LOG = Logger.getLogger(UserController.class);

    public static final int AUTOLOGIN = 60 * 60 * 24 * 14;

    public static final String LOGIN = "login";
    public static final String REG = "reg";
    public static final String MAIN = "main";
    public static final String RESULT = "result";
    public static final String SUCCESS = "success";

    @Resource
    private IUserService userService;
    @Resource
    private IUserLoginService userLoginService;

    @RequestMapping("/login.do")
    public String login() {
        return LOGIN;
    }

    @RequestMapping("/checkemail.json")
    public void checkEmail(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");

        boolean isExist = false;
        try {
            Rule r = RuleBuilder.build().addValidator(StringValidator.class).isNull(false);
            r.setErrorMessage("请填写邮件地址");
            Validate.check(email, "email", r);
        } catch (CheckFailureException e) {
            setOutput(req, e.getMessage());
            return;
        }

        isExist = userService.checkEmailExist(email);
        setOutput(req, isExist);
    }

    @RequestMapping("/reg.do")
    public String reg(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        user.setEmail(email);
        user.setPassword(password);
        user.setRegTime(new Timestamp(System.currentTimeMillis()));

        try {
            Validate.check(user);
        } catch (CheckFailureException e) {
            req.setAttribute(WebConst.OUTPUT_MESSAGE, e.getMessage());
        }

        try {
            userService.regUser(user);
        } catch (BizException e) {
            ErrorCode ec = e.getEc();
            req.setAttribute(ec.getCode(), ec.getMessage());
            return REG;
        }

        HttpSession session = req.getSession();
        session.setAttribute(WebConst.SESSION_USER, user);

        return SUCCESS;
    }

    @RequestMapping("/dologin.do")
    public String doLogin(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String autoLogin = req.getParameter("autologin");

        User user = null;
        try {
            user = userService.login(email, password);
        } catch (BizException e) {
            req.setAttribute(WebConst.REQ_INPUTEMAIL, email);
            ErrorCode ec = e.getEc();
            req.setAttribute(ec.getCode(), ec.getMessage());
            return LOGIN;
        }


        HttpSession session = req.getSession();
        session.setAttribute(WebConst.SESSION_USER, user);

        // 自动登录
        if (StringUtils.isNotBlank(autoLogin) && autoLogin.equals("on")) {
            Cookie cookieAutoLogin = new Cookie(WebConst.COOKIE_EMAIL, email);
            cookieAutoLogin.setMaxAge(AUTOLOGIN);
            resp.addCookie(cookieAutoLogin);
            Cookie cookieSessionId = new Cookie(WebConst.COOKIE_SESSIONID, session.getId());
            cookieSessionId.setMaxAge(AUTOLOGIN);
            resp.addCookie(cookieSessionId);

            try {
                userLoginService.saveUserLogin(email, session.getId(), true);
            } catch (BizException e) {
                LOG.error(e.getMessage(), e);
            }
        } else {
            try {
                userLoginService.saveUserLogin(email, session.getId(), false);
            } catch (BizException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return MAIN;
    }

    /**
     * 注销用户信息
     * 
     * @param req
     * @param resp
     */
    @RequestMapping("/delete.do")
    public void delete(HttpServletResponse req, HttpServletResponse resp) {
        // TODO: no implements
    }

}
