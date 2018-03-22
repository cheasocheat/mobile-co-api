package com.khmersolution.moduler.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DANG DIM
 * Date     : 3/21/2018, 1:57 PM
 * Email    : d.dim@gl-f.com
 */

public abstract class AbstractEntityResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("ID")
    protected long id;

    @JsonProperty("DESC")
    protected String desc;

    @JsonProperty("DESC_EN")
    protected String descEn;

    @JsonProperty("UPDATE_DATE")
    private String updateDate;

    @JsonProperty("CREATE_DATE")
    private String createDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDescEn() {
        return descEn;
    }

    public void setDescEn(String descEn) {
        this.descEn = descEn;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date date) {
        this.updateDate = new SimpleDateFormat("yyyy-M-dd hh:mm:ss.S").format(date);
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date date) {
        this.createDate = new SimpleDateFormat("yyyy-M-dd hh:mm:ss.S").format(date);
    }
}
