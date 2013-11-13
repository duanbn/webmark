package com.dy.webmark.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.dy.webmark.common.WebConst;

public class BaseController {

    public static final Logger LOG = Logger.getLogger(BaseController.class);

    protected void setOutput(HttpServletRequest req, Object output) {
        req.setAttribute(WebConst.OUTPUT_JSON, output);
    }

}
