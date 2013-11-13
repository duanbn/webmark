package com.dy.webmark.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dy.webmark.exception.BizException;

@Controller
public class IndexController extends BaseController {

    public static final Logger LOG = Logger.getLogger(UserController.class);

    @RequestMapping("/")
    public String login() throws BizException {
        return "login";
    }

}
