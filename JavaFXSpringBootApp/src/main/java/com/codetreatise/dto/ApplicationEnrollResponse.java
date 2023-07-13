package com.codetreatise.dto;

import com.codetreatise.common.ServiceError;

import java.io.Serializable;

public class ApplicationEnrollResponse implements Serializable {

    private boolean operationStatus;
    private ServiceError error;
    private Integer applicationId;
    private String referenceNumber;

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

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
}
