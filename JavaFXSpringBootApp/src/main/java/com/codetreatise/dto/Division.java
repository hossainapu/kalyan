package com.codetreatise.dto;

import java.io.Serializable;

public class Division implements Serializable {

    private Integer id;
    private String nameEn;
    private String nameBn;

    public Division() {
        super();
    }

    public Division(com.codetreatise.entity.Division divisionEO) {
        if(divisionEO != null) {
            this.id = divisionEO.getId();
            this.nameEn = divisionEO.getNameEn();
            this.nameBn = divisionEO.getNameBn();
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
}
