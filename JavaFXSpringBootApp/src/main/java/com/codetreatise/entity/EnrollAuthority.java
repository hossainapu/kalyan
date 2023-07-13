package com.codetreatise.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "selected_authority")
public class EnrollAuthority implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "CIRCLE")
    private String circle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
