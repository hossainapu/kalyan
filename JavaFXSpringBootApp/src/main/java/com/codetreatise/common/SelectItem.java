package com.codetreatise.common;

import com.codetreatise.dto.District;
import com.codetreatise.dto.Division;
import com.codetreatise.dto.Thana;

import java.io.Serializable;

public class SelectItem implements Serializable {

    private Integer id;
    private String name;

    public SelectItem() {
        super();
    }

    public SelectItem(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public SelectItem(Division division) {
        super();
        this.id = division.getId();
        this.name = division.getNameEn();
    }

    public SelectItem(District district) {
        super();
        this.id = district.getId();
        this.name = district.getNameEn();
    }

    public SelectItem(Thana thana) {
        super();
        this.id = thana.getId();
        this.name = thana.getNameEn();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
