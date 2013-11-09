package com.dy.webmark.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    public static final String LOGIN = "login.ftl";
    public static final String INDEX = "index.ftl";
    public static final String RESULT = "result.ftl";

    @Resource
    private IUserService userService;

    @RequestMapping("login.do")
    public String login(HttpServletResponse req, HttpServletResponse resp) {
        return "login";
    }

    @RequestMapping("/checkemail.json")
    public void checkEmail(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        try {
            Validate.check(email, "email", RuleBuilder.build().addValidator(StringValidator.class).isNull(false));
        } catch (CheckFailureException e) {
            setOutput(req, "请填写邮件地址");
            return;
        }

        boolean isExist = userService.checkEmailExist(email);
        setOutput(req, isExist);
    }

    @RequestMapping("/reg.do")
    public String reg(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User();
        try {
            PropertyUtils.copyProperties(user, req.getParameterMap());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        try {
            Validate.check(user);
        } catch (CheckFailureException e) {
            req.setAttribute(WebConst.OUTPUT_MESSAGE, e.getMessage());
        }

        try {
            userService.regUser(user);
        } catch (UserException e) {
            ErrorCode ec = e.getEc();
            req.setAttribute(WebConst.OUTPUT_MESSAGE, ec.getMessage());
            return LOGIN;
        }

        return INDEX;
    }

    @RequestMapping("/login.do")
    public String login(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = null;
        try {
            user = userService.login(email, password);
        } catch (UserException e) {
            req.setAttribute(WebConst.OUTPUT_MESSAGE, e.getEc().getMessage());
            return LOGIN;
        }

        HttpSession session = req.getSession();
        session.setAttribute(user.getEmail(), user);
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
