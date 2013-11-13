package com.dy.webmark.controller;

import javax.servlet.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.apache.log4j.*;

import com.dy.webmark.exception.BizException;

@Controller
@RequestMapping("/mark")
public class MarkController extends BaseController {

    public static final Logger LOG = Logger.getLogger(MarkController.class);

    public static final String SHOULU = "shoulu";

    @RequestMapping("/showdlg.do")
    public String showMarkDialog(HttpServletRequest req, HttpServletResponse resp) throws BizException {
        String title = req.getParameter("title");
        String url = req.getParameter("url");
        String keywords = req.getParameter("keywords");
        String desc = req.getParameter("desc");

        req.setAttribute("title", title);
        req.setAttribute("url", url);
        req.setAttribute("keywords", keywords);
        req.setAttribute("desc", desc);

        return SHOULU;
    }

}
