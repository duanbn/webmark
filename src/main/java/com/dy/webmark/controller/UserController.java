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
import org.springframework.web.util.WebUtils;

import com.duanbn.validation.Validate;
import com.dy.webmark.common.ValidateRule;
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

    @Resource
    private IUserService userService;
    @Resource
    private IUserLoginService userLoginService;

    // @RequestMapping("/login.do")
    // public String login() {
    // return "login";
    // }

    @RequestMapping("/checkemail.json")
    public void checkEmail(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");

        boolean isExist = false;
        Validate.check(email, "email", ValidateRule.emailRule);

        isExist = userService.checkEmailExist(email);
        returnData(req, isExist);
    }

    @RequestMapping("/reg.json")
    public void reg(HttpServletRequest req, HttpServletResponse resp) throws BizException {
        User user = new User();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        user.setEmail(email);
        user.setPassword(password);
        user.setRegTime(new Timestamp(System.currentTimeMillis()));

        Validate.check(user);

        userService.regUser(user);

        HttpSession session = req.getSession();
        session.setAttribute(WebConst.SESSION_USER, user);
    }

    @RequestMapping("/dologin.json")
    public void doLogin(HttpServletRequest req, HttpServletResponse resp) throws BizException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String autoLogin = req.getParameter("autologin");

        User user = userService.login(email, password);

        HttpSession session = getSession(req);
        session.setAttribute(WebConst.SESSION_USER, user);

        // 自动登录
        if (StringUtils.isNotBlank(autoLogin) && autoLogin.equals("on")) {
            Cookie cookieAutoLogin = new Cookie(WebConst.COOKIE_EMAIL, email);
            cookieAutoLogin.setPath("/");
            cookieAutoLogin.setMaxAge(AUTOLOGIN);
            resp.addCookie(cookieAutoLogin);

            Cookie cookieSessionId = new Cookie(WebConst.COOKIE_SESSIONID, session.getId());
            cookieSessionId.setPath("/");
            cookieSessionId.setMaxAge(AUTOLOGIN);
            resp.addCookie(cookieSessionId);

            userLoginService.updateUserLogin(email, session.getId(), true);
        } else {
            userLoginService.updateUserLogin(email, session.getId(), false);
        }
    }

    @RequestMapping("/dologout.json")
    public void doLogout(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = getSession(req);
        session.removeAttribute(WebConst.SESSION_USER);

        // 清理cookie
        Cookie emailCookie = WebUtils.getCookie(req, WebConst.COOKIE_EMAIL);
        if (emailCookie != null) {
            emailCookie.setPath("/");
            emailCookie.setMaxAge(0);
            resp.addCookie(emailCookie);
        }
        Cookie sessionsIdCookie = WebUtils.getCookie(req, WebConst.COOKIE_SESSIONID);
        if (sessionsIdCookie != null) {
            sessionsIdCookie.setPath("/");
            sessionsIdCookie.setMaxAge(0);
            resp.addCookie(sessionsIdCookie);
        }

    }

    /**
     * 注销用户信息
     * 
     * @param req
     * @param resp
     */
    @RequestMapping("/delete.do")
    public void delete(HttpServletResponse req, HttpServletResponse resp) throws BizException {
        // TODO: no implements
    }

}
