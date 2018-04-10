package com.khmersolution.moduler.domain.quotation;

import javax.swing.*;

/**
 * Created by DANG DIM
 * Date     : 4/9/2018, 8:48 AM
 * Email    : d.dim@gl-f.com
 */

public class MapQdRequired {

    private Spring product;
    private Long quotaId;
    private Long documentId;

    public Spring getProduct() {
        return product;
    }

    public void setProduct(Spring product) {
        this.product = product;
    }

    public Long getQuotaId() {
        return quotaId;
    }

    public void setQuotaId(Long quotaId) {
        this.quotaId = quotaId;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }
}
