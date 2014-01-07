package com.dy.webmark.mapper;

import java.util.List;
import java.util.Map;

/**
 * 收录计数sql
 * 
 * @author duanbn
 * 
 */
public class FavoriteCntSql {

    @SuppressWarnings("unchecked")
    public String selectByIds(Map<String, Object> param) {
        List<Integer> ids = (List<Integer>) param.get("ids");

        StringBuilder sql = new StringBuilder("select * from favoritecnt where f_favoid in (");
        for (int id : ids) {
            sql.append(id).append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        return sql.toString();
    }

}
