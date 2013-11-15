package com.dy.webmark.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duanbn.validation.Validate;
import com.dy.webmark.common.ErrorCode;
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
        User user = getUserInSession(req);
        if (user == null) {
            throw new BizException(ErrorCode.BIZ1002);
        }
        int userId = user.getId();

        FavoriteClip clip = new FavoriteClip();
        clip.setName(name);
        clip.setUserId(userId);

        Validate.check(clip);

        favoriteClipService.addFavoriteClip(clip);
    }

    @RequestMapping("/getFavoriteClip.json")
    public void getFavoriteClipWithJson(HttpServletRequest req, HttpServletResponse resp) throws BizException {

        User user = getUserInSession(req);

        List<FavoriteClip> clips = favoriteClipService.getFavoriteClip(user.getId());

        returnData(req, clips);
    }

}
