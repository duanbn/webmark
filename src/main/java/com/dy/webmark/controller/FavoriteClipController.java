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
import com.dy.webmark.entity.FavoriteClip;
import com.dy.webmark.entity.User;
import com.dy.webmark.exception.BizException;
import com.dy.webmark.service.IFavoriteClipService;
import com.dy.webmark.service.IFavoriteService;

@Controller
@RequestMapping("/favoriteclip")
public class FavoriteClipController extends BaseController {

    public static final Logger LOG = Logger.getLogger(FavoriteClipController.class);

    @Resource
    private IFavoriteClipService clipService;
    @Resource
    private IFavoriteService favoService;

    @RequestMapping("/clip.do")
    public String handleClip(HttpServletRequest req, HttpServletResponse resp) throws BizException {
        int clipId = StringUtil.converToInt(getParam(req, "clipId"), 0);
        if (clipId == 0) {
            throw new BizException(ErrorCode.BIZ4004);
        }

        // 获取优夹
        FavoriteClip clip = clipService.getById(clipId);
        // 获取此优夹的收录数并计算总页数
        long favoCnt = clipService.getFavoCnt(clipId);
        long totalpage = favoCnt % WebConst.DEFAULT_FAVORITE_PAGESIZE == 0 ? favoCnt
                / WebConst.DEFAULT_FAVORITE_PAGESIZE : favoCnt / WebConst.DEFAULT_FAVORITE_PAGESIZE + 1;
        // 获取用户所有的优夹
        User user = getUserInSession(req);
        List<FavoriteClip> clipList = clipService.getFavoriteClip(user.getU_id());

        req.setAttribute("user", user);
        req.setAttribute("clip", clip);
        req.setAttribute("totalPage", totalpage);
        req.setAttribute("clipId", clipId);
        req.setAttribute("cliplist", clipList);
        return "website";
    }

    @RequestMapping("/clipList.json")
    public void clipList(HttpServletRequest req, HttpServletResponse resp) throws BizException {
        int page = StringUtil.converToInt(getParam(req, "page"), 0);

        User user = getUserInSession(req);
        List<FavoriteClip> clipList = clipService.getFavoriteClip(user.getU_id(), true, page
                * WebConst.DEFAULT_CLIP_PAGESIZE, WebConst.DEFAULT_CLIP_PAGESIZE);
        returnData(req, clipList);
    }

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

        clipService.addFavoriteClip(clip);
    }

    @RequestMapping("/getFavoriteClipByNamePrefix.json")
    public void getFavoriteClipByNamePrefix(HttpServletRequest req, HttpServletResponse resp) throws BizException {
        String name = req.getParameter("name");
        Validate.check(name, ValidateRule.clipNameRule);

        User user = getUserInSession(req);

        List<FavoriteClip> clips = clipService.getByNamePrefix(user.getU_id(), name);

        returnData(req, clips);
    }

    @RequestMapping("/getFavoriteClip.json")
    public void getFavoriteClip(HttpServletRequest req, HttpServletResponse resp) throws BizException {
        int userId = getUserInSession(req).getU_id();
        Validate.check(userId, ValidateRule.userIdRule);

        List<FavoriteClip> clips = clipService.getFavoriteClip(userId);

        returnData(req, clips);
    }

}
