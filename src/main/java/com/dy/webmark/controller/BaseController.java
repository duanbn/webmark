package com.dy.webmark.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.dy.webmark.common.WebConst;
import com.dy.webmark.entity.User;

public class BaseController {

    public static final Logger LOG = Logger.getLogger(BaseController.class);

    protected void returnData(HttpServletRequest req, Object output) {
        req.setAttribute(WebConst.OUTPUT_DATA, output);
    }

    protected User getUserInSession(HttpServletRequest req) {
        HttpSession session = getSession(req);
        User user = (User) session.getAttribute(WebConst.SESSION_USER);
        return user;
    }

    protected HttpSession getSession(HttpServletRequest req) {
        HttpSession session = req.getSession();

        return session;
    }

    protected HttpSession getSession(HttpServletRequest req, boolean create) {
        HttpSession session = req.getSession(create);

        return session;
    }

    protected String getParam(HttpServletRequest req, String name) {
        String value = req.getParameter(name);
        if (value == null) {
            return "";
        }
        return value;
    }

}
