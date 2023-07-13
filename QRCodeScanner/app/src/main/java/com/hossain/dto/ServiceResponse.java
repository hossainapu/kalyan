package com.hossain.dto;

import java.io.Serializable;

public class ServiceResponse implements Serializable {

    private boolean operationStatus;
    private ServiceError error;

    public boolean isOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(boolean operationStatus) {
        this.operationStatus = operationStatus;
    }

    public ServiceError getError() {
        return error;
    }

    public void setError(ServiceError error) {
        this.error = error;
    }
}
