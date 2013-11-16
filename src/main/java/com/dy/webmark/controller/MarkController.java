package com.dy.webmark.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dy.webmark.common.Const;
import com.dy.webmark.common.WebConst;
import com.dy.webmark.entity.User;
import com.dy.webmark.exception.BizException;
import com.dy.webmark.service.IUserLoginService;
import com.dy.webmark.service.IUserService;

@Controller
@RequestMapping("/mark")
public class MarkController extends BaseController {

    public static final Logger LOG = Logger.getLogger(MarkController.class);

    @Resource
    private IUserService userService;
    @Resource
    private IUserLoginService userLoginService;

    @RequestMapping("/showdlg.do")
    public String showMarkDialog(HttpServletRequest req, HttpServletResponse resp) {
        String title = req.getParameter(Const.TITLE);
        String url = req.getParameter(Const.URL);
        String keywords = req.getParameter(Const.KEYWORDS);
        String desc = req.getParameter(Const.DESC);

        req.setAttribute(Const.TITLE, title);
        req.setAttribute(Const.URL, url);
        req.setAttribute(Const.KEYWORDS, keywords);
        req.setAttribute(Const.DESC, desc);

        return "shoulu";
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
            cookieAutoLogin.setMaxAge(WebConst.AUTOLOGIN);
            resp.addCookie(cookieAutoLogin);

            Cookie cookieSessionId = new Cookie(WebConst.COOKIE_SESSIONID, session.getId());
            cookieSessionId.setPath("/");
            cookieSessionId.setMaxAge(WebConst.AUTOLOGIN);
            resp.addCookie(cookieSessionId);

            userLoginService.updateUserLogin(email, session.getId(), true);
        } else {
            userLoginService.updateUserLogin(email, session.getId(), false);
        }

        Map<String, String> data = new HashMap<String, String>();
        data.put(Const.TITLE, (String) session.getAttribute(Const.TITLE));
        data.put(Const.URL, (String) session.getAttribute(Const.URL));
        data.put(Const.DESC, (String) session.getAttribute(Const.DESC));
        data.put(Const.KEYWORDS, (String) session.getAttribute(Const.KEYWORDS));
        WebConst.removeFavoriteFromSession(req);

        returnData(req, data);
    }

}
