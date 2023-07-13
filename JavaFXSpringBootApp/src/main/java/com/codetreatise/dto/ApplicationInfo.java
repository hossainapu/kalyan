package com.codetreatise.dto;

import java.io.Serializable;
import java.util.Date;

public class ApplicationInfo implements Serializable {

    private Integer id;
    private String officeCode;
    private String referenceNumber;
    private String nameEn;
    private String nameBn;
    private String fatherEn;
    private String fatherBn;
    private String spouseEn;
    private String spouseBn;
    private Date dateOfBirth;
    private String nid;
    private String bloodGroup;
    private String maritalStatus;
    private String emergencyName;
    private String emergencyRelation;
    private String gender;
    private String contactNumber;
    private String emergencyContact;
    private String presentDivision;
    private String presentDistrict;
    private String presentThana;
    private String presentAddressLine;
    private String permanentDivision;
    private String permanentDistrict;
    private String permanentThana;
    private String permanentAddressLine;
    private Integer numberOfEmi;
    private Integer numberOfEmiPaid;
    private String applicationType;
    private byte[] photo;
    private byte[] signature;
    private String language;
    private String bankTransactionNumber;
    private Integer status;
    private String strStatus;
    private String createdBy;
    private String updatedBy;
    private Date createdDate;
    private Date updatedDate;
    private boolean selected;

    public ApplicationInfo() {
        super();
    }

    public ApplicationInfo(com.codetreatise.entity.ApplicationInfo applicationInfo) {
        super();
        if(applicationInfo != null) {
            this.id = applicationInfo.getId();
            this.officeCode = applicationInfo.getOfficeCode();
            this.referenceNumber = applicationInfo.getReferenceNumber();
            this.nameEn = applicationInfo.getNameEn();
            this.nameBn = applicationInfo.getNameBn();
            this.fatherEn = applicationInfo.getFatherEn();
            this.fatherBn = applicationInfo.getFatherBn();
            this.spouseEn = applicationInfo.getSpouseEn();
            this.spouseBn = applicationInfo.getSpouseBn();
            this.dateOfBirth = applicationInfo.getDateOfBirth();
            this.nid = applicationInfo.getNid();
            this.emergencyName = applicationInfo.getEmergencyName();
            this.maritalStatus = applicationInfo.getMaritalStatus();
            this.bloodGroup = applicationInfo.getBloodGroup();
            this.emergencyRelation = applicationInfo.getEmergencyRelationship();
            this.gender = applicationInfo.getGender();
            this.contactNumber = applicationInfo.getContactNumber();
            this.emergencyContact = applicationInfo.getEmergencyContact();
            this.presentDivision = applicationInfo.getPresentDivision();
            this.presentDistrict = applicationInfo.getPresentDistrict();
            this.presentThana = applicationInfo.getPresentThana();
            this.presentAddressLine = applicationInfo.getPresentAddressLine();
            this.permanentDivision = applicationInfo.getPermanentDivision();
            this.permanentDistrict = applicationInfo.getPermanentDistrict();
            this.permanentThana = applicationInfo.getPermanentThana();
            this.permanentAddressLine = applicationInfo.getPermanentAddressLine();
            this.numberOfEmi = applicationInfo.getNumberOfEmi();
            this.numberOfEmiPaid = applicationInfo.getNumberOfEmiPaid();
            this.applicationType = applicationInfo.getApplicationType();
            this.photo = applicationInfo.getPhoto();
            this.signature = applicationInfo.getSignature();
            this.language = applicationInfo.getLanguage() != null  && applicationInfo.getLanguage().intValue() != 1 ? "ENGLISH" :"BANGLA";
            this.bankTransactionNumber = applicationInfo.getBankTransactionNumber();
            this.status = applicationInfo.getStatus();
            if(this.status == 1) {
                this.strStatus = "READY";
            }
            this.createdBy = applicationInfo.getCreatedBy();
            this.updatedBy = applicationInfo.getUpdatedBy();
            this.createdDate = applicationInfo.getCreatedDate();
            this.updatedDate = applicationInfo.getUpdatedDate();
            this.selected = false;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameBn() {
        return nameBn;
    }

    public void setNameBn(String nameBn) {
        this.nameBn = nameBn;
    }

    public String getFatherEn() {
        return fatherEn;
    }

    public void setFatherEn(String fatherEn) {
        this.fatherEn = fatherEn;
    }

    public String getFatherBn() {
        return fatherBn;
    }

    public void setFatherBn(String fatherBn) {
        this.fatherBn = fatherBn;
    }

    public String getSpouseEn() {
        return spouseEn;
    }

    public void setSpouseEn(String spouseEn) {
        this.spouseEn = spouseEn;
    }

    public String getSpouseBn() {
        return spouseBn;
    }

    public void setSpouseBn(String spouseBn) {
        this.spouseBn = spouseBn;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyRelation() {
        return emergencyRelation;
    }

    public void setEmergencyRelation(String emergencyRelation) {
        this.emergencyRelation = emergencyRelation;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getPresentDivision() {
        return presentDivision;
    }

    public void setPresentDivision(String presentDivision) {
        this.presentDivision = presentDivision;
    }

    public String getPresentDistrict() {
        return presentDistrict;
    }

    public void setPresentDistrict(String presentDistrict) {
        this.presentDistrict = presentDistrict;
    }

    public String getPresentThana() {
        return presentThana;
    }

    public void setPresentThana(String presentThana) {
        this.presentThana = presentThana;
    }

    public String getPresentAddressLine() {
        return presentAddressLine;
    }

    public void setPresentAddressLine(String presentAddressLine) {
        this.presentAddressLine = presentAddressLine;
    }

    public String getPermanentDivision() {
        return permanentDivision;
    }

    public void setPermanentDivision(String permanentDivision) {
        this.permanentDivision = permanentDivision;
    }

    public String getPermanentDistrict() {
        return permanentDistrict;
    }

    public void setPermanentDistrict(String permanentDistrict) {
        this.permanentDistrict = permanentDistrict;
    }

    public String getPermanentThana() {
        return permanentThana;
    }

    public void setPermanentThana(String permanentThana) {
        this.permanentThana = permanentThana;
    }

    public String getPermanentAddressLine() {
        return permanentAddressLine;
    }

    public void setPermanentAddressLine(String permanentAddressLine) {
        this.permanentAddressLine = permanentAddressLine;
    }

    public Integer getNumberOfEmi() {
        return numberOfEmi;
    }

    public void setNumberOfEmi(Integer numberOfEmi) {
        this.numberOfEmi = numberOfEmi;
    }

    public Integer getNumberOfEmiPaid() {
        return numberOfEmiPaid;
    }

    public void setNumberOfEmiPaid(Integer numberOfEmiPaid) {
        this.numberOfEmiPaid = numberOfEmiPaid;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getEmergencyName() {
        return emergencyName;
    }

    public void setEmergencyName(String emergencyName) {
        this.emergencyName = emergencyName;
    }

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getStrStatus() {
        return strStatus;
    }

    public void setStrStatus(String strStatus) {
        this.strStatus = strStatus;
    }

    public boolean isSelected() {
        return selected;
    }

    public String getBankTransactionNumber() {
        return bankTransactionNumber;
    }

    public void setBankTransactionNumber(String bankTransactionNumber) {
        this.bankTransactionNumber = bankTransactionNumber;
    }
}
