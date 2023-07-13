package com.codetreatise.dto;

import java.io.Serializable;

public class District implements Serializable {

    private Integer id;
    private String nameEn;
    private String nameBn;
    private Integer divisionId;

    public District() {
        super();
    }

    public District(com.codetreatise.entity.District districtEO) {
        super();
        if(districtEO != null) {
            this.id = districtEO.getId();
            this.nameEn = districtEO.getNameEn();
            this.nameBn = districtEO.getNameBn();
            this.divisionId = districtEO.getDivisionId();
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

    public Integer getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }
}
