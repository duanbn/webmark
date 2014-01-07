package com.dy.webmark.controller;

import java.sql.Timestamp;
import java.util.List;

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
import com.dy.webmark.entity.FavoriteClip;
import com.dy.webmark.entity.User;
import com.dy.webmark.exception.BizException;
import com.dy.webmark.service.IFavoriteClipService;
import com.dy.webmark.service.IFavoriteService;
import com.dy.webmark.service.IUserFollowingService;
import com.dy.webmark.service.IUserLoginService;
import com.dy.webmark.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    public static final Logger LOG = Logger.getLogger(UserController.class);

    @Resource
    private IUserService userService;
    @Resource
    private IUserLoginService userLoginService;
    @Resource
    private IFavoriteClipService clipService;
    @Resource
    private IFavoriteService favoService;
    @Resource
    private IUserFollowingService followingService;

    @RequestMapping("/clipList.json")
    public void clipList(HttpServletRequest req, HttpServletResponse resp) throws BizException {
        int start = Integer.parseInt(req.getParameter("start"));
        int limit = Integer.parseInt(req.getParameter("limit"));

        User user = getUserInSession(req);
        List<FavoriteClip> clipList = clipService.getFavoriteClip(user.getU_id(), start, limit);
        returnData(req, clipList);
    }

    @RequestMapping("/main.do")
    public String index(HttpServletRequest req, HttpServletResponse resp) throws BizException {
        User user = getUserInSession(req);

        // 获取用户优夹数
        long clipCnt = clipService.getClipCnt(user.getU_id());
        // 获取用户收录数
        long favoCnt = favoService.getFavoCnt(user.getU_id());
        // 获取用户喜欢数
        // TODO
        // 获取用户关注数
        long followingCnt = followingService.getFollowingCnt(user.getU_id());
        // 获取用户粉丝数
        long followerCnt = followingService.getFollowerCnt(user.getU_id());
        List<FavoriteClip> clipList = clipService.getFavoriteClip(user.getU_id(), 0, WebConst.DEFAULT_CLIP_PAGESIZE);

        if (LOG.isDebugEnabled()) {
            LOG.debug("clipcnt=" + clipCnt + ", favocnt=" + favoCnt + ", followingcnt=" + followingCnt
                    + ", followercnt=" + followerCnt);
        }

        req.setAttribute("clipcnt", clipCnt);
        req.setAttribute("favocnt", favoCnt);
        req.setAttribute("followingcnt", followingCnt);
        req.setAttribute("followercnt", followerCnt);
        req.setAttribute("clipList", clipList);
        return "main";
    }

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
        user.setU_email(email);
        user.setU_password(password);
        user.setU_regtime(new Timestamp(System.currentTimeMillis()));

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
