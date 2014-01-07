package com.dy.webmark.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import com.duanbn.common.util.DateUtils;
import com.dy.webmark.common.WebConst;
import com.dy.webmark.entity.User;
import com.dy.webmark.entity.UserLogin;
import com.dy.webmark.exception.BizException;
import com.dy.webmark.service.IUserLoginService;
import com.dy.webmark.service.IUserService;

@Controller
public class IndexController extends BaseController {

    public static final Logger LOG = Logger.getLogger(UserController.class);

    @Resource
    private IUserService userService;

    @Resource
    private IUserLoginService userLoginService;

    @RequestMapping("/")
    public String login(HttpServletRequest req, HttpServletResponse resp) throws BizException {
        User user = getUserInSession(req);
        if (user != null) {
            return "redirect:/favorite/main.do";
        }

        Cookie emailCookie = WebUtils.getCookie(req, WebConst.COOKIE_EMAIL);
        Cookie sessionsIdCookie = WebUtils.getCookie(req, WebConst.COOKIE_SESSIONID);
        if (user == null && emailCookie != null && sessionsIdCookie != null) {
            String email = emailCookie.getValue();
            String sessionId = sessionsIdCookie.getValue();
            UserLogin userLogin = userLoginService.getUserLogin(email, sessionId);

            if (userLogin != null) {
                HttpSession session = getSession(req);
                if (!userLogin.isUl_isautologin() || isExpired(userLogin.getUl_logintime())) {
                    return "login";
                }

                try {
                    user = userService.get(email);
                } catch (BizException e) {
                    return "login";
                }
                session.setAttribute(WebConst.SESSION_USER, user);

                return "redirect:/favorite/main.do";
            } else {
                return "login";
            }
        }
        return "login";
    }

    private boolean isExpired(Date lastLoginDate) {
        return DateUtils.getDifferDays(new Date(), lastLoginDate) > 14;
    }

}
