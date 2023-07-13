package com.hellokoding.auth.common;

import java.io.Serializable;

public class ServiceError implements Serializable {

    private int errorCode;
    private String message;

    public ServiceError() {
        super();
    }

    public ServiceError(int errorCode,String message) {
        super();
        this.errorCode = errorCode;
        switch (errorCode) {

            case ErrorCodes.ERROR_MISSING:
                this.message = String.format("Required parameter missing for :%s",message);
                break;

            case ErrorCodes.ERROR_DUPLICATE:
                this.message = String.format("Invalid parameter found for:%s",message);
                break;
            case ErrorCodes.ERROR_NOT_FOUND:
                this.message = String.format("Data not found for:%s",message);
                break;
            case ErrorCodes.ERROR_INVALID:
                this.message = String.format("Invalid value for:%s",message);
                break;
            default:
                this.message = message;
        }
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
