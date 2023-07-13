package com.codetreatise.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "APPLICATION_INFO")
public class ApplicationInfo implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "OFFICE_CODE")
    private String officeCode;

    @Column(name = "REFERENCE_NUMBER")
    private String referenceNumber;

    @Column(name = "NAME_EN")
    private String nameEn;

    @Column(name = "NAME_BN")
    private String nameBn;

    @Column(name = "FATHER_EN")
    private String fatherEn;

    @Column(name = "FATHER_BN")
    private String fatherBn;

    @Column(name = "SPOUSE_EN")
    private String spouseEn;

    @Column(name = "SPOUSE_BN")
    private String spouseBn;

    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(name = "NID")
    private String nid;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "BLOOD_GROUP")
    private String bloodGroup;

    @Column(name = "MARITAL_STATUS")
    private String maritalStatus;

    @Column(name = "EMERGENCY_NAME")
    private String emergencyName;

    @Column(name = "EMERGENCY_RELATIONSHIP")
    private String emergencyRelationship;

    @Column(name = "CONTACT_NUMBER")
    private String contactNumber;

    @Column(name = "EMERGENCY_CONTACT")
    private String emergencyContact;

    @Column(name = "PRESENT_DIVISION")
    private String presentDivision;

    @Column(name = "PRESENT_DISTRICT")
    private String presentDistrict;

    @Column(name = "PRESENT_THANA")
    private String presentThana;

    @Column(name = "PRESENT_ADDRESS_LINE")
    private String presentAddressLine;

    @Column(name = "PERMANENT_DIVISION")
    private String permanentDivision;

    @Column(name = "PERMANENT_DISTRICT")
    private String permanentDistrict;

    @Column(name = "PERMANENT_THANA")
    private String permanentThana;

    @Column(name = "PERMANENT_ADDRESS_LINE")
    private String permanentAddressLine;

    @Column(name = "NUMBER_OF_EMI")
    private Integer numberOfEmi;

    @Column(name = "NUMBER_OF_EMI_PAID")
    private Integer numberOfEmiPaid;

    @Column(name = "APPLICATION_TYPE")
    private String applicationType;

    @Column(name = "PHOTO")
    private byte[] photo;

    @Column(name = "SIGNATURE")
    private byte[] signature;

    @Column(name = "LANGUAGE")
    private Integer language;

    @Column(name = "BANK_TRANSACTION_NUMBER")
    private String bankTransactionNumber;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "CREATED_DATE")
    private Timestamp createdDate;

    @Column(name = "UPDATED_DATE")
    private Timestamp updatedDate;


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

    public String getEmergencyRelationship() {
        return emergencyRelationship;
    }

    public void setEmergencyRelationship(String emergencyRelationship) {
        this.emergencyRelationship = emergencyRelationship;
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

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
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

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
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

    public String getBankTransactionNumber() {
        return bankTransactionNumber;
    }

    public void setBankTransactionNumber(String bankTransactionNumber) {
        this.bankTransactionNumber = bankTransactionNumber;
    }
}
