package com.codetreatise.common;

import java.io.Serializable;

public class ServiceError implements Serializable {

    private Integer errorCode;
    private String errorMessage;

    public ServiceError() {
        super();
    }

    public ServiceError(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
