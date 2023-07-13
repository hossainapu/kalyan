package com.hellokoding.auth.model.dto;

import com.hellokoding.auth.common.Utils;
import java.io.Serializable;
import java.util.Base64;

public class ApplicationInfo implements Serializable {

    private Integer id;
    private String officeCode;
    private String circleCode;
    private String nameEn;
    private String nameBn;
    private String fatherEn;
    private String fatherBn;
    private String spouseEn;
    private String spouseBn;
    private String dateOfBirth;
    private String nid;
    private String contactNumber;
    private String emergencyContact;
    private String presentDivision;
    private String presentDistrict;
    private String presentThana;
    private String presentAddressLine;
    private String permanentDivision;
    private String permanentDistrict;
    private Integer numberOfEmiPaid;
    private Integer numberOfEmi;
    private String permanentAddressLine;
    private String permanentThana;
    private byte[] photo;
    private byte[] signature;
    private String photoStr;
    private String signatureStr;
    private String language;
    private String applicationType;
    private Integer status;
    private String uploadedBy;
    private String uploadedDate;
    private String createdBy;
    private String updatedBy;
    private String createdDate;
    private String updatedDate;
    private String gender;
    private String referenceNumber;
    private String bloodGroup;
    private String maritalStatus;
    private String emergencyName;
    private String emergencyRelation;
    private String bankTransactionNumber;

    public ApplicationInfo() {
        super();
    }

    public ApplicationInfo(com.hellokoding.auth.model.db.ApplicationInfo applicationInfo) {
        super();
        if(applicationInfo != null) {
            this.id = applicationInfo.getId();
            this.referenceNumber = applicationInfo.getReferenceNumber();
            this.nameEn = applicationInfo.getNameEn();
            this.nameBn = applicationInfo.getNameBn();
            this.fatherEn = applicationInfo.getFatherEn();
            this.fatherBn = applicationInfo.getFatherBn();
            this.spouseEn = applicationInfo.getSpouseEn();
            this.spouseBn = applicationInfo.getSpouseBn();
            this.dateOfBirth = Utils.dateToString(applicationInfo.getDateOfBirth());
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
            this.photoStr = Base64.getEncoder().encodeToString(applicationInfo.getPhoto());
            this.signatureStr = Base64.getEncoder().encodeToString(applicationInfo.getSignature());

            this.language = applicationInfo.getLanguage() != null  && applicationInfo.getLanguage().intValue() != 1 ? "ENGLISH" :"BANGLA";
            this.status = applicationInfo.getStatus();

            this.createdBy = applicationInfo.getCreatedBy();
            this.updatedBy = applicationInfo.getUpdatedBy();
            this.createdDate = Utils.dateToString(applicationInfo.getCreatedDate());
            this.updatedDate = Utils.dateToString(applicationInfo.getUpdatedDate());
            this.bankTransactionNumber = applicationInfo.getBankTransactionNumber();
            this.officeCode = applicationInfo.getOfficeCode();
            this.circleCode = "01";
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
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

    public Integer getNumberOfEmiPaid() {
        return numberOfEmiPaid;
    }

    public void setNumberOfEmiPaid(Integer numberOfEmiPaid) {
        this.numberOfEmiPaid = numberOfEmiPaid;
    }

    public Integer getNumberOfEmi() {
        return numberOfEmi;
    }

    public void setNumberOfEmi(Integer numberOfEmi) {
        this.numberOfEmi = numberOfEmi;
    }

    public String getPermanentAddressLine() {
        return permanentAddressLine;
    }

    public void setPermanentAddressLine(String permanentAddressLine) {
        this.permanentAddressLine = permanentAddressLine;
    }

    public String getPermanentThana() {
        return permanentThana;
    }

    public void setPermanentThana(String permanentThana) {
        this.permanentThana = permanentThana;
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

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public String getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(String uploadedDate) {
        this.uploadedDate = uploadedDate;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
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

    public String getEmergencyRelation() {
        return emergencyRelation;
    }

    public void setEmergencyRelation(String emergencyRelation) {
        this.emergencyRelation = emergencyRelation;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getPhotoStr() {
        return photoStr;
    }

    public void setPhotoStr(String photoStr) {
        this.photoStr = photoStr;
    }

    public String getSignatureStr() {
        return signatureStr;
    }

    public void setSignatureStr(String signatureStr) {
        this.signatureStr = signatureStr;
    }

    public String getBankTransactionNumber() {
        return bankTransactionNumber;
    }

    public void setBankTransactionNumber(String bankTransactionNumber) {
        this.bankTransactionNumber = bankTransactionNumber;
    }

    public String getCircleCode() {
        return circleCode;
    }

    public void setCircleCode(String circleCode) {
        this.circleCode = circleCode;
    }
}
