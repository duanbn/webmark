package com.dy.webmark.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.dy.webmark.common.WebConst;

public class BaseController {

    public static final Logger LOG = Logger.getLogger(BaseController.class);

    private static final ObjectMapper jsonMapper = new ObjectMapper();

    protected void setOutput(HttpServletRequest req, Object output) {
        try {
            String json = jsonMapper.writeValueAsString(output);
            req.setAttribute(WebConst.OUTPUT_JSON, json);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

}
