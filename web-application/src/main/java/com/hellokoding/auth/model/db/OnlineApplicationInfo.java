package com.hellokoding.auth.model.db;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "application_info_enroll")
public class OnlineApplicationInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "OFFICE_CODE")
    private String officeCode;

    @Column(name = "CIRCLE_CODE")
    private String circleCode;
    
    @Column(name="name_en")
    private String nameEn;

    @Column(name="name_bn")
    private String nameBn;

    @Column(name="father_en")
    private String fatherEn;

    @Column(name="father_bn")
    private String fatherBn;

    @Column(name="spouse_en")
    private String spouseEn;

    @Column(name="spouse_bn")
    private String spouseBn;

    @Column(name="date_of_birth")
    private Date dateOfBirth;

    @Column(name="nid")
    private String nid;

    @Column(name="contact_number")
    private String contactNumber;


    @Column(name="emergency_contact")
    private String emergencyContact;

    @Column(name="present_division")
    private String presentDivision;

    @Column(name="present_district")
    private String presentDistrict;

    @Column(name="present_thana")
    private String presentThana;

    @Column(name="present_address_line")
    private String presentAddressLine;

    @Column(name="permanent_division")
    private String permanentDivision;

    @Column(name="permanent_district")
    private String permanentDistrict;

    @Column(name="permanent_thana")
    private String permanentThana;

    @Column(name="permanent_address_line")
    private String permanentAddressLine;

    @Column(name="number_of_emi")
    private Integer numberOfEmi;

    @Column(name="number_of_emi_paid")
    private Integer numberOfEmiPaid;

    @Column(name="application_type")
    private String applicationType;

    @Column(name="photo")
    private byte[] photo;

    @Column(name="signature")
    private byte[] signature;

    @Column(name="language")
    private Integer language;

    @Column(name="status")
    private Integer status;

    @Column(name="uploaded_by")
    private String uploadedBy;

    @Column(name="uploaded_date")
    private Timestamp uploadedDate;

    @Column(name="created_by")
    private String createdBy;

    @Column(name="updated_by")
    private String updatedBy;

    @Column(name="created_date")
    private Timestamp createdDate;

    @Column(name="updated_date")
    private Timestamp updatedDate;

    @Column(name="gender")
    private String gender;

    @Column(name="reference_number")
    private String referenceNumber;

    @Column(name="blood_group")
    private String bloodGroup;

    @Column(name="marital_status")
    private String maritalStatus;

    @Column(name="emergency_name")
    private String emergencyName;

    @Column(name="emergency_relationship")
    private String emergencyRelationship;

    @Column(name = "UUID")
    private String uuid;

    @Column(name = "BANK_TRANSACTION_NUMBER")
    private String bankTransactionNumber;

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

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public Timestamp getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(Timestamp uploadedDate) {
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

    public String getEmergencyRelationship() {
        return emergencyRelationship;
    }

    public void setEmergencyRelationship(String emergencyRelationship) {
        this.emergencyRelationship = emergencyRelationship;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
