package com.hossain.dto;

import java.io.Serializable;

public class PasswordChangeResponse extends ServiceResponse implements Serializable {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
