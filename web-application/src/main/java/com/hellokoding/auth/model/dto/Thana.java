package com.hellokoding.auth.model.dto;

import java.io.Serializable;

public class Thana implements Serializable {

    private Integer id;
    private String nameEn;
    private String nameBn;
    private Integer districtId;

    public Thana() {
        super();
    }

    public Thana(com.hellokoding.auth.model.db.Thana thana) {
        if(thana != null) {
            this.id = thana.getId();
            this.nameEn = thana.getNameEn();
            this.nameBn = thana.getNameBn();
            this.districtId = thana.getDistrictId();
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

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }
}
