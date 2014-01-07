package com.dy.webmark.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duanbn.validation.Validate;
import com.dy.webmark.common.ValidateRule;
import com.dy.webmark.entity.Favorite;
import com.dy.webmark.entity.FavoriteClip;
import com.dy.webmark.entity.User;
import com.dy.webmark.exception.BizException;
import com.dy.webmark.service.IFavoriteClipService;
import com.dy.webmark.service.IFavoriteService;

@Controller
@RequestMapping("/favorite")
public class FavoriteController extends BaseController {

    public static final Logger LOG = Logger.getLogger(FavoriteController.class);

    @Resource
    private IFavoriteService favoService;

    @Resource
    private IFavoriteClipService clipService;

    @RequestMapping("/add.json")
    public void handleAddFavorite(HttpServletRequest req, HttpServletResponse resp) throws BizException {
        User user = getUserInSession(req);

        String clipName = req.getParameter("clipName");
        FavoriteClip clip = clipService.getByName(user.getU_id(), clipName);

        String title = req.getParameter("title");
        String desc = req.getParameter("desc");
        String keyword = req.getParameter("keyword");
        String url = req.getParameter("url");
        String screenshot = req.getParameter("screenshot");

        Validate.check(screenshot, ValidateRule.fileNameRule);

        Favorite favo = new Favorite();
        favo.setF_userid(user.getU_id());
        favo.setF_desc(desc);
        favo.setF_keyword(keyword);
        favo.setF_title(title);
        favo.setF_url(url);
        favo.setF_screenshot(screenshot);
        favo.setF_clipid(clip.getFc_id());

        Validate.check(favo);

        favoService.addFavorite(favo);
    }

    @RequestMapping("/main.do")
    public String index(HttpServletRequest req, HttpServletResponse resp) throws BizException {
        req.setAttribute("name", "spring freemarker");

        return "main";
    }

}
