package com.hellokoding.auth.model.db;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "sms_log")
public class SMSLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="sms_text")
    private String smsText;

    @Column(name="mobile_number")
    private String mobileNumber;

    @Column(name="uuid")
    private String uuid;

    @Column(name="uuid_ssl")
    private String uuidSSL;

    @Column(name="send_by")
    private String sendBy;

    @Column(name="creation_date")
    private Timestamp creationDate;

    @Column(name="send_status")
    private String sendStatus;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuidSSL() {
        return uuidSSL;
    }

    public void setUuidSSL(String uuidSSL) {
        this.uuidSSL = uuidSSL;
    }

    public String getSendBy() {
        return sendBy;
    }

    public void setSendBy(String sendBy) {
        this.sendBy = sendBy;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }
}
