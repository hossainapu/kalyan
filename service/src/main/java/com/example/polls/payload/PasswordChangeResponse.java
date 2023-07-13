package com.example.polls.payload;

import com.example.polls.util.ServiceError;
import com.example.polls.util.ServiceResponse;

import java.io.Serializable;

public class PasswordChangeResponse extends ServiceResponse implements Serializable {

    private String message;

    public PasswordChangeResponse() {
        super();
    }

    public PasswordChangeResponse(ServiceError error) {
        super(error);
    }

    public PasswordChangeResponse(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
