package com.codetreatise.dto;

import com.codetreatise.common.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class EnrollApplication implements Serializable {

    private Integer id;
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

    public EnrollApplication() {
        super();
    }

    public EnrollApplication(ApplicationInfo applicationInfo) {
        super();
        if(applicationInfo != null) {
            this.id = applicationInfo.getId();
            this.nameEn = applicationInfo.getNameEn();
            this.nameBn = applicationInfo.getNameBn();
            this.fatherEn = applicationInfo.getFatherEn();
            this.fatherBn = applicationInfo.getFatherBn();
            this.spouseEn = applicationInfo.getSpouseEn();
            this.spouseBn = applicationInfo.getSpouseBn();
            this.dateOfBirth = Utils.dateToString(applicationInfo.getDateOfBirth());
            this.nid = applicationInfo.getNid();
            this.contactNumber = applicationInfo.getContactNumber();
            this.emergencyContact = applicationInfo.getEmergencyContact();
            this.presentDivision = applicationInfo.getPresentDivision();
            this.presentDistrict = applicationInfo.getPresentDistrict();
            this.presentThana = applicationInfo.getPresentThana();
            this.presentAddressLine = applicationInfo.getPresentAddressLine();
            this.permanentDivision = applicationInfo.getPermanentDivision();
            this.permanentDistrict = applicationInfo.getPermanentDistrict();
            this.numberOfEmiPaid = applicationInfo.getNumberOfEmiPaid();
            this.numberOfEmi = applicationInfo.getNumberOfEmi();
            this.permanentAddressLine = applicationInfo.getPermanentAddressLine();
            this.permanentThana = applicationInfo.getPermanentThana();
            try {
                System.out.println("---BEFORE SIZE:"+applicationInfo.getPhoto().length);
                ByteArrayInputStream bis = new ByteArrayInputStream(applicationInfo.getPhoto());
                BufferedImage p = Utils.resizeImage(ImageIO.read(bis),300,300);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(p, "png", baos);
                this.photo = baos.toByteArray();
                System.out.println("---AFTER SIZE:"+this.photo.length);

                bis = new ByteArrayInputStream(applicationInfo.getSignature());
                p = Utils.resizeImage(ImageIO.read(bis),300,300);
                baos = new ByteArrayOutputStream();
                ImageIO.write(p,"png",baos);
                this.signature = baos.toByteArray();
            } catch (Throwable t) {

            }
            this.language = applicationInfo.getLanguage();
            this.applicationType = applicationInfo.getApplicationType();
            this.status = applicationInfo.getStatus();
            this.createdBy = applicationInfo.getCreatedBy();
            this.updatedBy = applicationInfo.getUpdatedBy();
            this.createdDate = Utils.dateToString(applicationInfo.getCreatedDate());
            this.updatedDate = Utils.dateToString(applicationInfo.getUpdatedDate());
            this.gender = applicationInfo.getGender();
            this.referenceNumber = applicationInfo.getReferenceNumber();
            this.bloodGroup = applicationInfo.getBloodGroup();
            this.maritalStatus = applicationInfo.getMaritalStatus();
            this.emergencyName = applicationInfo.getEmergencyName();
            this.emergencyRelation = applicationInfo.getEmergencyRelation();
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
}
