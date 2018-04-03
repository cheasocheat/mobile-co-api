package com.khmersolution.moduler.domain.quotation;

import com.khmersolution.moduler.domain.AbstractEntityResponse;

/**
 * Created by DANG DIM
 * Date     : 3/29/2018, 9:53 AM
 * Email    : d.dim@gl-f.com
 */

public class Quotation extends AbstractEntityResponse {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String dateOfBirth;
    private Long provinceId;
    private String gender;
    private String trxNo;
    private String quotaStatus;
    private String quotaPrevStatus;
    private String quotationReference;
    private Long dealerId;
    private Long coId;
    private Long wayKnowId;
    private String firstSubmissionDate;
    private String quotationDate;
    private String declineDate;
    private String rejectedDate;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTrxNo() {
        return trxNo;
    }

    public void setTrxNo(String trxNo) {
        this.trxNo = trxNo;
    }

    public String getQuotaStatus() {
        return quotaStatus;
    }

    public void setQuotaStatus(String quotaStatus) {
        this.quotaStatus = quotaStatus;
    }

    public String getQuotaPrevStatus() {
        return quotaPrevStatus;
    }

    public void setQuotaPrevStatus(String quotaPrevStatus) {
        this.quotaPrevStatus = quotaPrevStatus;
    }

    public String getQuotationReference() {
        return quotationReference;
    }

    public void setQuotationReference(String quotationReference) {
        this.quotationReference = quotationReference;
    }

    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }

    public Long getCoId() {
        return coId;
    }

    public void setCoId(Long coId) {
        this.coId = coId;
    }

    public Long getWayKnowId() {
        return wayKnowId;
    }

    public void setWayKnowId(Long wayKnowId) {
        this.wayKnowId = wayKnowId;
    }

    public String getFirstSubmissionDate() {
        return firstSubmissionDate;
    }

    public void setFirstSubmissionDate(String firstSubmissionDate) {
        this.firstSubmissionDate = firstSubmissionDate;
    }

    public String getQuotationDate() {
        return quotationDate;
    }

    public void setQuotationDate(String quotationDate) {
        this.quotationDate = quotationDate;
    }

    public String getDeclineDate() {
        return declineDate;
    }

    public void setDeclineDate(String declineDate) {
        this.declineDate = declineDate;
    }

    public String getRejectedDate() {
        return rejectedDate;
    }

    public void setRejectedDate(String rejectedDate) {
        this.rejectedDate = rejectedDate;
    }
}
