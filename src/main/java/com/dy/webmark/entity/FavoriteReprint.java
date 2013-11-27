package com.dy.webmark.entity;

import com.duanbn.mydao.annotation.Field;
import com.duanbn.mydao.annotation.PrimaryKey;
import com.duanbn.mydao.annotation.Table;

/**
 * 转录表
 * 
 * @author duanbn
 * 
 */
@Table
public class FavoriteReprint {

    @PrimaryKey(autoIncrement = true)
    private int id; // 主键

    @Field(isCanNull = false)
    private int fromFavoId; // 被转录的收录id

    @Field(isCanNull = false)
    private int userId; // 转录人的id

    @Field(isCanNull = false)
    private int clipId; // 转录到优夹id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromFavoId() {
        return fromFavoId;
    }

    public void setFromFavoId(int fromFavoId) {
        this.fromFavoId = fromFavoId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getClipId() {
        return clipId;
    }

    public void setClipId(int clipId) {
        this.clipId = clipId;
    }

}
