package com.khmersolution.moduler.domain.response.document;

/**
 * Created by DANG DIM
 * Date     : 3/28/2018, 10:44 AM
 * Email    : d.dim@gl-f.com
 */

public class QDocument {

    private Long quotationId;
    private String date;

    public Long getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(Long quotationId) {
        this.quotationId = quotationId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
