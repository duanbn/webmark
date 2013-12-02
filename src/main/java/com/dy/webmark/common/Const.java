package com.dy.webmark.common;

import com.duanbn.mydao.util.PropertiesUtil;

public class Const {

    public static final String TITLE = "title";
    public static final String URL = "url";
    public static final String KEYWORDS = "keywords";
    public static final String DESC = "desc";
    public static final String SCREENTSHOT = "screenshot";

    //
    // 网页截图相关
    //
    public static final String NOSCREEN = "noscreen.jpg"; // 无图片
    public static final String TOOL_PATH = PropertiesUtil.get("phantomjs.path"); 
    public static final String TOOL = TOOL_PATH + "/" + "phantomjs";
    public static final String TOOL_JS = TOOL_PATH + "/" + "screenshot.js";
    public static final String SCREEN_TEMP_PATH = PropertiesUtil.get("screenshot.temp.path");
    public static final String SCREEN_PATH = PropertiesUtil.get("screenshot.root.path");
    public static final String SCREEN_TIMEOUT = PropertiesUtil.get("screenshot.timeout");
    public static final int IMAGE_WIDTH = PropertiesUtil.getInt("image.width");
    public static final int IMAGE_MAXHEIGHT = PropertiesUtil.getInt("image.maxheight");
    public static final int THUM_WIDTH = PropertiesUtil.getInt("thum.width");
    public static final int THUM_MAXHEIGHT = PropertiesUtil.getInt("thum.maxheight");
}
