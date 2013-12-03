package com.dy.webmark.mapper;

import java.util.List;
import java.util.Map;

public class UserFollowingSql {

    public String updateReadBatch(Map<String, Object> param) {
        int userId = (Integer) param.get("userId");
        List<Integer> followerIds = (List<Integer>) param.get("followerIds");

        StringBuilder sql = new StringBuilder("update userfollowing set isRead=1 where followingId=");
        sql.append(userId).append(" and userId in (");
        for (Integer id : followerIds) {
            sql.append(id).append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        return sql.toString();
    }

}
