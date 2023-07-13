package com.hellokoding.auth.model.dto;

import com.hellokoding.auth.common.Utils;

import java.io.Serializable;

public class EnrolledApplication implements Serializable {
    private Integer id;
    private String name;
    private String referenceNumber;
    private String contactNumber;
    private Integer installmentPaid;
    private String emergencyContact;
    private String authority;
    private String applicationType;

    public EnrolledApplication() {
        super();
    }

    public EnrolledApplication(Object[] result) {
        if(result != null && result.length >0) {
            if(Utils.indexValueExists(result,0)) {
                this.id = Utils.getInteger(result[0]);
            }
            if(Utils.indexValueExists(result,1)) {
                this.name = (String)result[1];
            }
            if(Utils.indexValueExists(result,2)) {
                this.referenceNumber = (String)result[2];
            }
            if(Utils.indexValueExists(result,3)) {
                this.contactNumber = (String)result[3];
            }
            if(Utils.indexValueExists(result,4)) {
                this.installmentPaid = Utils.getInteger(result[4]);
            }
            if(Utils.indexValueExists(result,5)) {
                this.emergencyContact = (String)result[5];
            }
            if(Utils.indexValueExists(result,6)) {
                this.authority = (String)result[6];
            }
            if(Utils.indexValueExists(result,7)) {
                this.applicationType = (String) result[7];
            }
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Integer getInstallmentPaid() {
        return installmentPaid;
    }

    public void setInstallmentPaid(Integer installmentPaid) {
        this.installmentPaid = installmentPaid;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }
}
