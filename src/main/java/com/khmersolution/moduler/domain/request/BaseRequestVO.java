package com.khmersolution.moduler.domain.request;

/*
Create By: Ron Rith
Create Date: 3/30/2018
*/
public class BaseRequestVO {
    private Long id;
    private String lastUpdateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
