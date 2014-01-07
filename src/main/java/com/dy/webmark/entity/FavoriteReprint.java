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
@Indexes({ @Index(field = "fromFavoId, userId") })
public class FavoriteReprint {

    @PrimaryKey(autoIncrement = false)
    private int fr_id; // 主键

    @Field(isCanNull = false)
    private int fr_fromfavoid; // 被转录的收录id

    @Field(isCanNull = false)
    private int fr_userid; // 转录人的id

    @Field(isCanNull = false)
    private int fr_clipid; // 转录到优夹id

    public FavoriteReprint() {

    }

    public FavoriteReprint(int favoId) {
        this.fr_fromfavoid = favoId;
    }

    public int getFr_id() {
        return fr_id;
    }

    public void setFr_id(int fr_id) {
        this.fr_id = fr_id;
    }

    public int getFr_fromfavoid() {
        return fr_fromfavoid;
    }

    public void setFr_fromfavoid(int fr_fromfavoid) {
        this.fr_fromfavoid = fr_fromfavoid;
    }

    public int getFr_userid() {
        return fr_userid;
    }

    public void setFr_userid(int fr_userid) {
        this.fr_userid = fr_userid;
    }

    public int getFr_clipid() {
        return fr_clipid;
    }

    public void setFr_clipid(int fr_clipid) {
        this.fr_clipid = fr_clipid;
    }

}
