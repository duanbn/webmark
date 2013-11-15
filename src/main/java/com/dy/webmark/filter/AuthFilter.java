package com.dy.webmark.filter;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.util.WebUtils;

import com.duanbn.common.util.DateUtils;
import com.duanbn.mydao.util.PropertiesUtil;
import com.dy.webmark.common.WebConst;
import com.dy.webmark.entity.User;
import com.dy.webmark.entity.UserLogin;
import com.dy.webmark.exception.BizException;
import com.dy.webmark.service.IUserLoginService;
import com.dy.webmark.service.IUserService;

public class AuthFilter implements Filter {

    private boolean isEnableDebug = PropertiesUtil.getBoolean("enable.debug");

    @Resource
    private IUserService userService;

    private IUserLoginService userLoginService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        if (!isEnableDebug) {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession session = req.getSession(true);

            String uri = ((HttpServletRequest) request).getRequestURI();

            if (!uri.endsWith("view/login.html") && !uri.endsWith("dologin.json")) {
                User user = (User) session.getAttribute(WebConst.SESSION_USER);
                if (user == null) {
                    Cookie emailCookie = WebUtils.getCookie(req, WebConst.COOKIE_EMAIL);
                    Cookie sessionsIdCookie = WebUtils.getCookie(req, WebConst.COOKIE_SESSIONID);
                    if (emailCookie != null && sessionsIdCookie != null) {
                        String email = emailCookie.getValue();
                        String sessionId = sessionsIdCookie.getValue();
                        UserLogin userLogin = userLoginService.getUserLogin(email, sessionId);

                        if (userLogin != null) {
                            if (!userLogin.isAutoLogin() || isExpired(userLogin.getLoginTime())) {
                                resp.sendRedirect(req.getContextPath() + "/view/login.html");
                                return;
                            }

                            try {
                                user = userService.get(email);
                            } catch (BizException e) {
                                resp.sendRedirect(req.getContextPath() + "/view/login.html");
                                return;
                            }
                            session.setAttribute(WebConst.SESSION_USER, user);
                        } else {
                            resp.sendRedirect(req.getContextPath() + "/view/login.html");
                            return;
                        }
                    } else {
                        resp.sendRedirect(req.getContextPath() + "/view/login.html");
                        return;
                    }
                }
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    private boolean isExpired(Date lastLoginDate) {
        return DateUtils.getDifferDays(new Date(), lastLoginDate) > 14;
    }
}
