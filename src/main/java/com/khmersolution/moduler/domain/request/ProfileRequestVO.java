package com.khmersolution.moduler.domain.request;

/**
 * Created by DANG DIM
 * Date     : 4/10/2018, 3:16 PM
 * Email    : d.dim@gl-f.com
 */

public class ProfileRequestVO {
    private Long coId;
    private String token;
    private String updatedDate;

    public Long getCoId() {
        return coId;
    }

    public void setCoId(Long coId) {
        this.coId = coId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public ProfileRequestVO(Long coId, String token, String updatedDate) {
        this.coId = coId;
        this.token = token;
        this.updatedDate = updatedDate;
    }

    public ProfileRequestVO() {
    }
}
