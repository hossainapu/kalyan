package com.hellokoding.auth.common;

import com.hellokoding.auth.model.dto.District;
import com.hellokoding.auth.model.dto.Division;
import com.hellokoding.auth.model.dto.Thana;

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
        this.name = division.getNameBn();
    }

    public SelectItem(District district) {
        super();
        this.id = district.getId();
        this.name = district.getNameBn();
    }

    public SelectItem(Thana thana) {
        super();
        this.id = thana.getId();
        this.name = thana.getNameBn();
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
