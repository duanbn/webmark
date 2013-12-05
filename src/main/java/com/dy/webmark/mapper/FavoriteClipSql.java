package com.dy.webmark.mapper;

import java.util.Map;

public class FavoriteClipSql {

    public String selectByIds(Map<String, Object> param) {
        int[] ids = (int[]) param.get("ids");

        StringBuilder sql = new StringBuilder("select * from favoriteclip where id in (");
        for (int id : ids) {
            sql.append(id).append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        return sql.toString();
    }

}
