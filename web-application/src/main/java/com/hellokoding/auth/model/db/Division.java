package com.hellokoding.auth.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "DIVISION")
public class Division implements Serializable {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME_EN")
    private String nameEn;

    @Column(name = "NAME_BN")
    private String nameBn;

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
