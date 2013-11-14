package com.dy.webmark.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dy.webmark.common.WebConst;
import com.dy.webmark.entity.Favorite;
import com.dy.webmark.exception.BizException;
import com.dy.webmark.service.IFavoriteService;

@Controller
@RequestMapping("/favorite")
public class FavoriteController extends BaseController {

    public static final Logger LOG = Logger.getLogger(FavoriteController.class);

    @Resource
    private IFavoriteService favoService;

    @RequestMapping("/add.do")
    public String addfavorite(HttpServletRequest req, HttpServletRequest resp) {
        int userId = Integer.parseInt(req.getParameter("userId"));
        String title = req.getParameter("title");
        String desc = req.getParameter("desc");
        String keyword = req.getParameter("keyword");
        String url = req.getParameter("url");

        Favorite favo = new Favorite();
        favo.setUserId(userId);
        favo.setDescription(desc);
        favo.setKeyword(keyword);
        favo.setTitle(title);
        favo.setUrl(url);

        try {
            favoService.addFavorite(favo);
        } catch (BizException e) {
            LOG.error(e);
            req.setAttribute(WebConst.OUTPUT_ERROR, e.getEc());
            // TODO: 如果添加失败去哪？？？
        }

        return WebConst.VIEW_MAIN;
    }

    @RequestMapping("/main.do")
    public String index(HttpServletRequest req, HttpServletResponse resp) throws BizException {
        req.setAttribute("name", "spring freemarker");

        return WebConst.VIEW_MAIN;
    }

}
