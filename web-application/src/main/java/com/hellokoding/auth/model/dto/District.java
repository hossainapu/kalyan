package com.hellokoding.auth.model.dto;

import java.io.Serializable;

public class District implements Serializable {
    private Integer id;
    private String nameEn;
    private String nameBn;
    private Integer divisionId;

    public District() {
        super();
    }

    public District(com.hellokoding.auth.model.db.District district) {
        if(district != null) {
            this.id = district.getId();
            this.nameEn = district.getNameEn();
            this.nameBn = district.getNameBn();
            this.divisionId = district.getDivisionId();
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
