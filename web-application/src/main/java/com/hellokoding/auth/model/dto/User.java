package com.hellokoding.auth.model.dto;

import java.io.Serializable;

public class User implements Serializable {

    private Integer id;
    private String name;
    private String username;
    private String contactNumber;
    private String email;
    private String password;
    private String passwordConfirm;
    private String roleName;

    public User() {
        super();
    }

    public User(com.hellokoding.auth.model.db.User userEO) {
        if(userEO != null) {
            this.id = userEO.getId();
            this.name = userEO.getName();
            this.username = userEO.getUsername();
            this.contactNumber = userEO.getContactNumber();
            this.email = userEO.getEmail();
            this.password = userEO.getPassword();
            this.passwordConfirm = userEO.getPassword();
            this.roleName = userEO.getRoleName();
        }
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
