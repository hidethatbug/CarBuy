package com.bawei.carbuydome1.entry;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author:程金柱
 * Date:2019/6/15 17:44
 * Description：
 */
@Entity
public class JsonBean {
    @Id(autoincrement = true)
    private long id;
    private String json;
    @Generated(hash = 310326824)
    public JsonBean(long id, String json) {
        this.id = id;
        this.json = json;
    }
    @Generated(hash = 1926928967)
    public JsonBean() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getJson() {
        return this.json;
    }
    public void setJson(String json) {
        this.json = json;
    }
}
