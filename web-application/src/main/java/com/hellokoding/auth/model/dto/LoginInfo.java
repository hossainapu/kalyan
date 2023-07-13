package com.hellokoding.auth.model.dto;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Arrays;

public class LoginInfo extends User {

    private Integer id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String userRole;
    private String authorityCode;

    public LoginInfo(com.hellokoding.auth.model.db.User userEO) {
        super(userEO.getUsername(), userEO.getPassword(), Arrays.asList(new SimpleGrantedAuthority(userEO.getRoleName())));
        this.id = userEO.getId();
        this.name = userEO.getName();
        this.username = userEO.getUsername();
        this.email = userEO.getEmail();
        this.password = userEO.getPassword();
        this.userRole = userEO.getRoleName();
        this.authorityCode = userEO.getAuthorityCode();
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
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getAuthorityCode() {
        return authorityCode;
    }

    public void setAuthorityCode(String authorityCode) {
        this.authorityCode = authorityCode;
    }
}
