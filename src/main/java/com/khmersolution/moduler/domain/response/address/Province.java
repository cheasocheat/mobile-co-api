package com.khmersolution.moduler.domain.response.address;

import org.codehaus.jackson.annotate.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DANG DIM
 * Date     : 3/19/2018, 2:52 PM
 * Email    : d.dim@gl-f.com
 */

public class Province {

    @JsonProperty("ID")
    protected long id;
    @JsonProperty("DESC")
    protected String desc;
    @JsonProperty("DESC_EN")
    protected String descEn;
    @JsonProperty("UPDATE_DATE")
    private String updateDate;
    @JsonProperty("PROVINCE_CODE")
    private String code;
    @JsonProperty("CREATE_DATE")
    private String createDate;
    @JsonProperty("PRODUCT")
    private String product;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date date) {
        this.createDate = new SimpleDateFormat("yyyy-M-dd hh:mm:ss.S").format(date);
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
