package com.dy.webmark.service.impl;

import java.awt.Rectangle;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;

import org.apache.commons.codec.digest.DigestUtils;
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
import com.dy.webmark.service.IFavoriteClipService;
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

    @Resource
    private IFavoriteClipService clipService;

    @Override
    public long getFavoCnt(int userId) {
        return favoMapper.selectCnt(userId);
    }

    @Override
    public List<Favorite> getByClip(int userId, int clipId, int start, int limit) {
        return getByClip(userId, clipId, false, start, limit);
    }

    @Override
    public List<Favorite> getByClip(int userId, int clipId, boolean hasCnt, int start, int limit) {
        List<Favorite> rst = favoMapper.selectByClip(userId, clipId, start, limit);
        if (rst == null || rst.isEmpty()) {
            return Collections.emptyList();
        }

        if (hasCnt) {
            List<Integer> ids = new ArrayList<Integer>();
            Map<Integer, Favorite> map = new HashMap<Integer, Favorite>();
            for (Favorite favo : rst) {
                ids.add(favo.getF_id());
                map.put(favo.getF_id(), favo);
            }
            List<FavoriteCnt> cnts = favoCntMapper.selectByFavoIds(ids);

            Favorite favo = null;
            for (FavoriteCnt cnt : cnts) {
                favo = map.get(cnt.getF_favoid());
                if (favo != null) {
                    favo.setCnt(cnt);
                }
            }
            return new ArrayList<Favorite>(map.values());
        }

        return rst;
    }

    @Override
    public String genSreentshot(String url) throws BizException {
        // 检查此url是否已经有截图
        String fileName = favoMapper.getByUrl(url);
        if (fileName != null) {
            return "image/" + fileName;
        }

        // 生成截图
        String name = null;
        try {
            name = DigestUtils.md5Hex(url.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e1) {
            throw new BizException(ErrorCode.BIZ2007);
        }
        fileName = name + ".jpg";
        String thumFileName = name + "_thum.jpg";
        String target = Const.SCREEN_TEMP_PATH + "/" + fileName;
        String thumFile = Const.SCREEN_TEMP_PATH + "/" + thumFileName;

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
            if (code == 0) {
                handleThum(target, thumFile, Const.THUM_WIDTH, Const.THUM_MAXHEIGHT);
            }
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
        Favorite f = favoMapper.getByURL(favo.getF_userid(), favo.getF_url());
        if (f != null) {
            throw new BizException(ErrorCode.BIZ2006);
        }

        try {
            // 添加收录
            favo.setF_screenshot(favo.getF_screenshot().substring(favo.getF_screenshot().indexOf("/") + 1));
            favoMapper.insertFavorite(favo);

            // 初始化收录计数
            FavoriteCnt cnt = new FavoriteCnt();
            cnt.setF_favoid(favo.getF_id());
            favoCntMapper.addFavoriteCnt(cnt);

            // 修改优夹计数
            clipService.incrFavoCnt(favo.getF_clipid());

            // 将网页截图放入正式目录
            String fileName = favo.getF_screenshot();
            String thumFileName = fileName.substring(0, fileName.lastIndexOf(".")) + "_thum.jpg";
            if (!fileName.equals(Const.NOSCREEN) && !FileUtils.getFile(Const.SCREEN_PATH, fileName).exists()) {
                // 生成图片
                String destFilePath = Const.SCREEN_PATH + "/" + fileName;
                String srcFilePath = Const.SCREEN_TEMP_PATH + "/" + fileName;
                handleThum(srcFilePath, destFilePath, Const.IMAGE_WIDTH, Const.IMAGE_MAXHEIGHT);
                // 将缩略图移动到image目录
                File srcThumFile = new File(Const.SCREEN_TEMP_PATH + "/" + thumFileName);
                File destThumFile = new File(Const.SCREEN_PATH + "/" + thumFileName);
                FileUtils.moveFile(srcThumFile, destThumFile);

                File srcFile = new File(Const.SCREEN_TEMP_PATH + "/" + favo.getF_screenshot());
                FileUtils.deleteQuietly(srcFile);
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
        reprintFavo.setF_isreprint(true);
        reprintFavo.setF_clipid(clipId);
        reprintFavo.setF_userid(userId);
        reprintFavo.setF_desc(favo.getF_desc());
        reprintFavo.setF_keyword(favo.getF_keyword());
        reprintFavo.setF_title(favo.getF_title());
        reprintFavo.setF_url(favo.getF_url());
        reprintFavo.setF_screenshot(favo.getF_screenshot());
        favoMapper.insertFavorite(reprintFavo);
        // 初始化收录计数
        FavoriteCnt cnt = new FavoriteCnt();
        cnt.setF_favoid(reprintFavo.getF_id());
        favoCntMapper.addFavoriteCnt(cnt);

        // 添加转录信息
        fr = new FavoriteReprint(reprintFavo.getF_id());
        fr.setFr_clipid(clipId);
        fr.setFr_fromfavoid(favoId);
        fr.setFr_userid(userId);
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
                    userIds[i] = reprints.get(i).getFr_userid();
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

    private void handleThum(String srcPath, String tarPath, int width, int maxHeigth) throws MagickException {
        // 生成缩略图
        ImageInfo srcImageInfo = new ImageInfo(srcPath);
        MagickImage srcImage = new MagickImage(srcImageInfo);
        double w = srcImage.getDimension().getWidth();
        double h = srcImage.getDimension().getHeight();

        // 缩略图
        int HEIGHT = (int) (h * width / w);
        MagickImage thumImage = srcImage.scaleImage(width, HEIGHT);
        w = thumImage.getDimension().getWidth();
        h = thumImage.getDimension().getHeight();

        // 剪切
        if (h > maxHeigth) {
            Rectangle r = new Rectangle((int) w, maxHeigth);
            thumImage = thumImage.cropImage(r);
        }

        // 锐化
        thumImage = thumImage.sharpenImage(1, 1);
        thumImage.setFileName(tarPath);
        thumImage.writeImage(srcImageInfo);
    }

}
