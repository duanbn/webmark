package com.dy.webmark.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duanbn.mydao.util.StringUtil;
import com.duanbn.validation.Validate;
import com.dy.webmark.common.ErrorCode;
import com.dy.webmark.common.ValidateRule;
import com.dy.webmark.entity.FavoriteClip;
import com.dy.webmark.entity.User;
import com.dy.webmark.exception.BizException;
import com.dy.webmark.service.IFavoriteClipService;

@Controller
@RequestMapping("/favoriteclip")
public class FavoriteClipController extends BaseController {

    public static final Logger LOG = Logger.getLogger(FavoriteClipController.class);

    @Resource
    private IFavoriteClipService favoriteClipService;

    @RequestMapping("/add.json")
    public void addFavoriteClip(HttpServletRequest req, HttpServletResponse resp) throws BizException {
        String name = req.getParameter("name");
        Validate.check(name, ValidateRule.clipNameRule);

        User user = getUserInSession(req);
        if (user == null) {
            throw new BizException(ErrorCode.BIZ1002);
        }
        int userId = user.getU_id();

        FavoriteClip clip = new FavoriteClip();
        clip.setFc_name(name);
        clip.setFc_userid(userId);

        Validate.check(clip);

        favoriteClipService.addFavoriteClip(clip);
    }

    @RequestMapping("/getFavoriteClipByNamePrefix.json")
    public void getFavoriteClipByNamePrefix(HttpServletRequest req, HttpServletResponse resp) throws BizException {
        String name = req.getParameter("name");
        Validate.check(name, ValidateRule.clipNameRule);

        User user = getUserInSession(req);

        List<FavoriteClip> clips = favoriteClipService.getByNamePrefix(user.getU_id(), name);

        returnData(req, clips);
    }

    @RequestMapping("/getFavoriteClip.json")
    public void getFavoriteClip(HttpServletRequest req, HttpServletResponse resp) throws BizException {
        int userId = StringUtil.converToInt(req.getParameter("userId"), 0);
        Validate.check(userId, ValidateRule.userIdRule);

        int start = StringUtil.converToInt(req.getParameter("start"), 0);
        Validate.check(start, ValidateRule.startRule);

        int limit = StringUtil.converToInt(req.getParameter("limit"), 10);
        Validate.check(limit, ValidateRule.limitRule);

        List<FavoriteClip> clips = favoriteClipService.getFavoriteClip(userId, start, limit);

        returnData(req, clips);
    }

}
