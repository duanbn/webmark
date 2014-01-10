package com.dy.webmark.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duanbn.common.util.StringUtil;
import com.duanbn.validation.Validate;
import com.dy.webmark.common.ErrorCode;
import com.dy.webmark.common.ValidateRule;
import com.dy.webmark.common.WebConst;
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

    @RequestMapping("/favolist.json")
    public void handleFavoriteList(HttpServletRequest req, HttpServletResponse resp) throws BizException {
        int page = StringUtil.converToInt(getParam(req, "page"), 0);
        int clipId = StringUtil.converToInt(getParam(req, "clipId"), 0);
        if (clipId == 0) {
            throw new BizException(ErrorCode.BIZ4004);
        }

        List<Favorite> favoList = favoService.getByClip(clipId, true, page * WebConst.DEFAULT_FAVORITE_PAGESIZE,
                WebConst.DEFAULT_FAVORITE_PAGESIZE);

        returnData(req, favoList);
    }

    @RequestMapping("/add.json")
    public void handleAddFavorite(HttpServletRequest req, HttpServletResponse resp) throws BizException {
        User user = getUserInSession(req);

        String clipName = req.getParameter("clipName");
        FavoriteClip clip = clipService.getByName(user.getU_id(), clipName);

        String title = getParam(req, "title");
        String desc = getParam(req, "desc");
        String keyword = getParam(req, "keyword");
        String url = getParam(req, "url");
        String screenshot = getParam(req, "screenshot");

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

}
