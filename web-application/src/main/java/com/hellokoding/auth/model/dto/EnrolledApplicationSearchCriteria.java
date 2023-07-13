package com.hellokoding.auth.model.dto;

import java.io.Serializable;

public class EnrolledApplicationSearchCriteria implements Serializable {

    private String name;
    private String contactNumber;
    private String fatherName;
    private String authorityCode;
    private Integer installmentPaid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getAuthorityCode() {
        return authorityCode;
    }

    public void setAuthorityCode(String authorityCode) {
        this.authorityCode = authorityCode;
    }

    public Integer getInstallmentPaid() {
        return installmentPaid;
    }

    public void setInstallmentPaid(Integer installmentPaid) {
        this.installmentPaid = installmentPaid;
    }
}
