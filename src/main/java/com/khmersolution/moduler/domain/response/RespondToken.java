package com.khmersolution.moduler.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khmersolution.moduler.domain.SecUser;

import java.io.Serializable;

/*
Create By: Ron Rith
Create Date: 4/9/2018
*/
public class RespondToken implements Serializable {
    private static final long serialVersionUID = 1L;

    //@JsonProperty("RSLT_MSG")
    protected String RSLT_MSG;
    //@JsonProperty("RSLT_CD")
    protected String RSLT_CD;
    //@JsonProperty("RSLT_DATA")
    protected SecUser RSLT_DATA;

    public RespondToken() {
    }

    public String getRSLT_MSG() {
        return RSLT_MSG;
    }

    public void setRSLT_MSG(String RSLT_MSG) {
        this.RSLT_MSG = RSLT_MSG;
    }

    public String getRSLT_CD() {
        return RSLT_CD;
    }

    public void setRSLT_CD(String RSLT_CD) {
        this.RSLT_CD = RSLT_CD;
    }

    public SecUser getRSLT_DATA() {
        return RSLT_DATA;
    }

    public void setRSLT_DATA(SecUser RSLT_DATA) {
        this.RSLT_DATA = RSLT_DATA;
    }

    public RespondToken(String RSLT_MSG, String RSLT_CD, SecUser RSLT_DATA) {
        this.RSLT_MSG = RSLT_MSG;
        this.RSLT_CD = RSLT_CD;
        this.RSLT_DATA = RSLT_DATA;
    }
}
