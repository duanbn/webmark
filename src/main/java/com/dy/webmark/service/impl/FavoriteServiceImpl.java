package com.dy.webmark.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.dy.webmark.common.Const;
import com.dy.webmark.common.ErrorCode;
import com.dy.webmark.entity.Favorite;
import com.dy.webmark.entity.FavoriteCnt;
import com.dy.webmark.entity.FavoriteReprint;
import com.dy.webmark.entity.User;
import com.dy.webmark.exception.BizException;
import com.dy.webmark.mapper.FavoriteCntMapper;
import com.dy.webmark.mapper.FavoriteMapper;
import com.dy.webmark.mapper.FavoriteReprintMapper;
import com.dy.webmark.service.IFavoriteService;
import com.dy.webmark.service.IUserService;

@Service
public class FavoriteServiceImpl implements IFavoriteService {

    public static final Logger LOG = Logger.getLogger(FavoriteServiceImpl.class);

    public static final Runtime rt = Runtime.getRuntime();

    @Resource
    private FavoriteMapper favoMapper;
    @Resource
    private FavoriteReprintMapper favoReprintMapper;
    @Resource
    private FavoriteCntMapper favoCntMapper;

    @Resource
    private IUserService userService;

    @Override
    public String genSreentshot(String url) throws BizException {
        // 检查此url是否已经有截图
        String fileName = favoMapper.getByUrl(url);
        if (fileName != null) {
            return "image/" + fileName;
        }

        fileName = UUID.randomUUID().toString() + ".jpg";

        String target = Const.SCREEN_TEMP_PATH + "/" + fileName;
        StringBuilder cmd = new StringBuilder();
        cmd.append(Const.TOOL).append(" ");
        cmd.append(Const.TOOL_JS).append(" ");
        cmd.append(url).append(" ");
        cmd.append(target).append(" ");
        cmd.append(Const.SCREEN_TIMEOUT);
        Process p;
        int code = 0;
        try {
            p = rt.exec(cmd.toString());
            code = p.waitFor();
        } catch (Exception e) {
            throw new BizException(ErrorCode.BIZ2007, e);
        }

        if (code == 1) {
            throw new BizException(ErrorCode.BIZ2007);
        }

        return "image_temp/" + fileName;
    }

    @Override
    public void addFavorite(Favorite favo) throws BizException {
        // 检查收录是否存在
        Favorite f = favoMapper.getByURL(favo.getUserId(), favo.getUrl());
        if (f != null) {
            throw new BizException(ErrorCode.BIZ2006);
        }

        try {
            favoMapper.insertFavorite(favo);

            // 初始化收录计数
            FavoriteCnt cnt = new FavoriteCnt();
            cnt.setFavoId(favo.getId());
            favoCntMapper.addFavoriteCnt(cnt);

            // 将网页截图放入正式目录
            if (!favo.getScreenshot().equals(Const.NOSCREEN)
                    && FileUtils.getFile(Const.SCREEN_PATH, favo.getScreenshot()) == null) {
                String destFilePath = Const.SCREEN_PATH + "/" + favo.getScreenshot();
                File srcFile = new File(Const.SCREEN_TEMP_PATH + "/" + favo.getScreenshot());
                File destFile = new File(destFilePath);
                FileUtils.moveFile(srcFile, destFile);
            }

        } catch (Exception e) {
            throw new BizException(ErrorCode.BIZ2001, e);
        }
    }

    @Override
    public void reprintFavorite(int favoId, int userId, int clipId) throws BizException {
        FavoriteReprint fr = favoReprintMapper.getFavoriteReprint(favoId, userId);
        if (fr != null) {
            throw new BizException(ErrorCode.BIZ6002);
        }

        Favorite favo = getFavoriteById(favoId);

        // 添加转录
        Favorite reprintFavo = new Favorite();
        reprintFavo.setReprint(true);
        reprintFavo.setClipId(clipId);
        reprintFavo.setUserId(userId);
        reprintFavo.setDescription(favo.getDescription());
        reprintFavo.setKeyword(favo.getKeyword());
        reprintFavo.setTitle(favo.getTitle());
        reprintFavo.setUrl(favo.getUrl());
        reprintFavo.setScreenshot(favo.getScreenshot());
        favoMapper.insertFavorite(reprintFavo);
        // 初始化收录计数
        FavoriteCnt cnt = new FavoriteCnt();
        cnt.setFavoId(reprintFavo.getId());
        favoCntMapper.addFavoriteCnt(cnt);

        // 添加转录信息
        fr = new FavoriteReprint(reprintFavo.getId());
        fr.setClipId(clipId);
        fr.setFromFavoId(favoId);
        fr.setUserId(userId);
        favoReprintMapper.insertFavoriteReprint(fr);

        // 被转录的收录数加1
        incrReprint(favoId);
    }

    @Override
    public Favorite getFavoriteById(int favoId) throws BizException {
        return getFavoriteById(favoId, false, false);
    }

    @Override
    public Favorite getFavoriteById(int favoId, boolean isReprintList, boolean isCnt) throws BizException {
        Favorite favo = favoMapper.getFavoriteById(favoId);

        if (favo == null) {
            throw new BizException(ErrorCode.BIZ2002);
        }

        if (isReprintList) {
            List<FavoriteReprint> reprints = favoReprintMapper.getFavoriteReprintList(favoId);
            if (reprints != null && !reprints.isEmpty()) {
                int[] userIds = new int[reprints.size()];
                for (int i = 0; i < reprints.size(); i++) {
                    userIds[i] = reprints.get(i).getUserId();
                }

                List<User> users = userService.getUserByIds(userIds);

                favo.setReprintUserList(users);
            } else {
                favo.setReprintUserList(new ArrayList<User>());
            }
        }

        if (isCnt) {
            FavoriteCnt favoCnt = favoCntMapper.get(favoId);
            favo.setCnt(favoCnt);
        }

        return favo;
    }

    @Override
    public void incrPopluar(int favoId) throws BizException {
        try {
            favoCntMapper.incrPopluar(favoId);
        } catch (Exception e) {
            throw new BizException(ErrorCode.BIZ2004, e);
        }
    }

    @Override
    public void incrLike(int favoId) throws BizException {
        try {
            favoCntMapper.incrLike(favoId);
        } catch (Exception e) {
            throw new BizException(ErrorCode.BIZ2003, e);
        }
    }

    @Override
    public void incrReprint(int favoId) throws BizException {
        try {
            favoCntMapper.incrReprint(favoId);
        } catch (Exception e) {
            throw new BizException(ErrorCode.BIZ2005, e);
        }
    }

}
