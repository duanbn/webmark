package com.dy.webmark.entity;

import com.duanbn.mydao.annotation.Field;
import com.duanbn.mydao.annotation.Index;
import com.duanbn.mydao.annotation.Indexes;
import com.duanbn.mydao.annotation.PrimaryKey;
import com.duanbn.mydao.annotation.Table;

/**
 * 转录信息表
 * 
 * @author duanbn
 * 
 */
@Table
@Indexes({ @Index(field = "favoId, userId") })
public class FavoriteReprint {

    @PrimaryKey(autoIncrement = true)
    private int id; // 主键

    @Field(isCanNull = false)
    private int favoId; // 被转录的收录id

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

    public int getFavoId() {
        return favoId;
    }

    public void setFavoId(int favoId) {
        this.favoId = favoId;
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
