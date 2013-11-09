package com.dy.webmark.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duanbn.validation.Rule;
import com.duanbn.validation.RuleBuilder;
import com.duanbn.validation.Validate;
import com.duanbn.validation.exception.CheckFailureException;
import com.duanbn.validation.validator.impl.StringValidator;
import com.dy.webmark.common.WebConst;
import com.dy.webmark.entity.User;
import com.dy.webmark.exception.ErrorCode;
import com.dy.webmark.exception.UserException;
import com.dy.webmark.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    public static final Logger LOG = Logger.getLogger(UserController.class);

    public static final String LOGIN = "login";
    public static final String REG = "reg";
    public static final String INDEX = "index";
    public static final String RESULT = "result";

    @Resource
    private IUserService userService;

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

        try {
            Validate.check(user);
        } catch (CheckFailureException e) {
            req.setAttribute(WebConst.OUTPUT_MESSAGE, e.getMessage());
        }

        try {
            userService.regUser(user);
        } catch (UserException e) {
            ErrorCode ec = e.getEc();
            req.setAttribute(ec.getCode(), ec.getMessage());
            return REG;
        }

        HttpSession session = req.getSession();
        session.setAttribute(WebConst.SESSION_USER, user);

        return INDEX;
    }

    @RequestMapping("/dologin.do")
    public String doLogin(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = null;
        try {
            user = userService.login(email, password);
        } catch (UserException e) {
            req.setAttribute(WebConst.REQ_INPUTEMAIL, email);
            ErrorCode ec = e.getEc();
            req.setAttribute(ec.getCode(), ec.getMessage());
            return LOGIN;
        }


        HttpSession session = req.getSession();
        session.setAttribute(WebConst.SESSION_USER, user);
        return INDEX;
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
