package com.dy.webmark.mapper;

import java.util.Map;

public class UserSql {

    public String selectByIds(Map<String, Object> param) {
        int[] ids = (int[]) param.get("ids");

        StringBuilder sql = new StringBuilder("select * from user where u_id in (");
        for (int id : ids) {
            sql.append(id).append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        return sql.toString();
    }

}
