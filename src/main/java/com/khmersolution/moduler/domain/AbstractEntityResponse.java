package com.khmersolution.moduler.domain;

import java.io.Serializable;

/**
 * Created by DANG DIM
 * Date     : 3/21/2018, 1:57 PM
 * Email    : d.dim@gl-f.com
 */

public abstract class AbstractEntityResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Long id;
    protected String recordStatus;
    protected String createdDate;
    protected String updatedDate;
    protected String product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
