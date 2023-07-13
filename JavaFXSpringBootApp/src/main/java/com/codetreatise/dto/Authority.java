package com.codetreatise.dto;

import java.io.Serializable;

public class Authority implements Serializable {

    private Integer id;
    private String nameEn;
    private String nameBn;
    private String code;
    private String circle;

    public Authority() {
        super();
    }

    public Authority(Integer id,String code,String circle) {
        super();
        this.id = id;
        this.code = code;
        this.circle = circle;
    }

    public Authority(com.codetreatise.entity.Authority authorityEO) {
        super();
        if(authorityEO != null) {
            this.id = authorityEO.getId();
            this.nameEn = authorityEO.getNameEn();
            this.nameBn = authorityEO.getNameBn();
            this.code = authorityEO.getCode();
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCircle() {
        return circle;
    }

    public void setCircle(String circle) {
        this.circle = circle;
    }

    @Override
    public String toString() {
        return this.nameEn;
    }
}
