package com.hellokoding.auth.model.dto;

import com.hellokoding.auth.common.Utils;

import java.io.Serializable;
import java.util.Base64;

public class OnlineApplicationInfo implements Serializable {

    private Integer id;
    private String uuid;
    private String nameEn;
    private String nameBn;
    private String fatherEn;
    private String fatherBn;
    private String gender;
    private String dateOfBirth;
    private String nid;
    private String maritalStatus;
    private String bloodGroup;
    private String spouseEn;
    private String spouseBn;
    private String applicationType;
    private Integer installmentPaid;
    private String bankTransactionNumber;
    private String authorityCode;
    private String circleCode;
    private String contactNumber;
    private String emergencyName;
    private String emergencyNumber;
    private String emergencyRelation;
    private String presentDivision;
    private String permanentDivision;
    private String presentDistrict;
    private String permanentDistrict;
    private String presentUpozila;
    private String permanentUpozila;
    private String presentAddressLine;
    private String permanentAddressLine;
    private byte[] photo;
    private byte[] signature;
    private String photoStr;
    private String signatureStr;

    public OnlineApplicationInfo() {
        super();
    }

    public OnlineApplicationInfo(com.hellokoding.auth.model.db.OnlineApplicationInfo applicationInfoEO) {
        if (applicationInfoEO != null) {
            this.id = applicationInfoEO.getId();
            this.uuid = applicationInfoEO.getUuid();
            this.nameEn = applicationInfoEO.getNameEn();
            this.nameBn = applicationInfoEO.getNameBn();
            this.fatherEn = applicationInfoEO.getFatherEn();
            this.fatherBn = applicationInfoEO.getFatherBn();
            this.gender = applicationInfoEO.getGender();
            this.nid = applicationInfoEO.getNid();
            this.dateOfBirth = Utils.dateToString(applicationInfoEO.getDateOfBirth());
            this.maritalStatus = applicationInfoEO.getMaritalStatus();
            this.bloodGroup = applicationInfoEO.getBloodGroup();
            this.spouseEn = applicationInfoEO.getSpouseEn();
            this.spouseBn = applicationInfoEO.getSpouseBn();
            this.applicationType = applicationInfoEO.getApplicationType();
            this.installmentPaid = applicationInfoEO.getNumberOfEmiPaid();
            this.bankTransactionNumber = applicationInfoEO.getBankTransactionNumber();
            this.authorityCode = applicationInfoEO.getOfficeCode();
            this.circleCode = applicationInfoEO.getCircleCode();
            this.contactNumber = applicationInfoEO.getContactNumber();
            this.emergencyName = applicationInfoEO.getEmergencyName();
            this.emergencyNumber = applicationInfoEO.getEmergencyContact();
            this.emergencyRelation = applicationInfoEO.getEmergencyRelationship();
            this.presentDivision = applicationInfoEO.getPresentDivision();
            this.permanentDivision = applicationInfoEO.getPermanentDivision();
            this.presentDistrict = applicationInfoEO.getPresentDistrict();
            this.permanentDistrict = applicationInfoEO.getPermanentDistrict();
            this.presentUpozila = applicationInfoEO.getPresentThana();
            this.permanentUpozila = applicationInfoEO.getPermanentThana();
            this.presentAddressLine = applicationInfoEO.getPresentAddressLine();
            this.permanentAddressLine = applicationInfoEO.getPermanentAddressLine();
            //this.photo = applicationInfoEO.getPhoto();
            //this.signature = applicationInfoEO.getSignature();
            this.photoStr = Base64.getEncoder().encodeToString(applicationInfoEO.getPhoto());
            this.signatureStr = Base64.getEncoder().encodeToString(applicationInfoEO.getSignature());
        }
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
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

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public Integer getInstallmentPaid() {
        return installmentPaid;
    }

    public void setInstallmentPaid(Integer installmentPaid) {
        this.installmentPaid = installmentPaid;
    }

    public String getBankTransactionNumber() {
        return bankTransactionNumber;
    }

    public void setBankTransactionNumber(String bankTransactionNumber) {
        this.bankTransactionNumber = bankTransactionNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmergencyName() {
        return emergencyName;
    }

    public void setEmergencyName(String emergencyName) {
        this.emergencyName = emergencyName;
    }

    public String getEmergencyNumber() {
        return emergencyNumber;
    }

    public void setEmergencyNumber(String emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
    }

    public String getEmergencyRelation() {
        return emergencyRelation;
    }

    public void setEmergencyRelation(String emergencyRelation) {
        this.emergencyRelation = emergencyRelation;
    }

    public String getPresentDivision() {
        return presentDivision;
    }

    public void setPresentDivision(String presentDivision) {
        this.presentDivision = presentDivision;
    }

    public String getPermanentDivision() {
        return permanentDivision;
    }

    public void setPermanentDivision(String permanentDivision) {
        this.permanentDivision = permanentDivision;
    }

    public String getPresentDistrict() {
        return presentDistrict;
    }

    public void setPresentDistrict(String presentDistrict) {
        this.presentDistrict = presentDistrict;
    }

    public String getPermanentDistrict() {
        return permanentDistrict;
    }

    public void setPermanentDistrict(String permanentDistrict) {
        this.permanentDistrict = permanentDistrict;
    }

    public String getPresentUpozila() {
        return presentUpozila;
    }

    public void setPresentUpozila(String presentUpozila) {
        this.presentUpozila = presentUpozila;
    }

    public String getPermanentUpozila() {
        return permanentUpozila;
    }

    public void setPermanentUpozila(String permanentUpozila) {
        this.permanentUpozila = permanentUpozila;
    }

    public String getPresentAddressLine() {
        return presentAddressLine;
    }

    public void setPresentAddressLine(String presentAddressLine) {
        this.presentAddressLine = presentAddressLine;
    }

    public String getPermanentAddressLine() {
        return permanentAddressLine;
    }

    public void setPermanentAddressLine(String permanentAddressLine) {
        this.permanentAddressLine = permanentAddressLine;
    }

    public String getAuthorityCode() {
        return authorityCode;
    }

    public void setAuthorityCode(String authorityCode) {
        this.authorityCode = authorityCode;
    }

    public String getCircleCode() {
        return circleCode;
    }

    public void setCircleCode(String circleCode) {
        this.circleCode = circleCode;
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

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
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
}
