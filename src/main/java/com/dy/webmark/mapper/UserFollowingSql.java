package com.dy.webmark.mapper;

import java.util.List;
import java.util.Map;

public class UserFollowingSql {

    @SuppressWarnings("unchecked")
    public String updateReadBatch(Map<String, Object> param) {
        int userId = (Integer) param.get("userId");
        List<Integer> followerIds = (List<Integer>) param.get("followerIds");

        StringBuilder sql = new StringBuilder("update userfollowing set uf_isread=1 where uf_followingid=");
        sql.append(userId).append(" and userId in (");
        for (Integer id : followerIds) {
            sql.append(id).append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        return sql.toString();
    }

}
