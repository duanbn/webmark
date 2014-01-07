package com.dy.webmark.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class WebConst {

    public static final int DEFAULT_CLIP_PAGESIZE = 10;

    public static final String OUTPUT_DATA = "output_data";
    public static final String OUTPUT_ERROR = "output_error";
    public static final String OUTPUT_MESSAGE = "output_message";

    public static final String REQ_INPUTURL = "inputurl";
    public static final String REQ_INPUTEMAIL = "inputemail";

    public static final String SESSION_USER = "user";
    public static final String SESSION_ERRORINFO = "errorinfo";

    public static final String COOKIE_EMAIL = "email";
    public static final String COOKIE_SESSIONID = "sessionid";

    public static final int AUTOLOGIN = 60 * 60 * 24 * 14;


    public static void saveFavoriteInSession(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute(Const.TITLE, req.getParameter(Const.TITLE));
        session.setAttribute(Const.DESC, req.getParameter(Const.DESC));
        session.setAttribute(Const.KEYWORDS, req.getParameter(Const.KEYWORDS));
        session.setAttribute(Const.URL, req.getParameter(Const.URL));
    }

    public static void removeFavoriteFromSession(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.removeAttribute(Const.DESC);
        session.removeAttribute(Const.KEYWORDS);
        session.removeAttribute(Const.TITLE);
        session.removeAttribute(Const.URL);
    }
}
