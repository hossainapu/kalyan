package com.codetreatise.dto;

import com.codetreatise.common.ServiceError;

import java.io.Serializable;

public class OnlineApplicationResponse implements Serializable {

    private boolean operationStatus;
    private ServiceError error;
    private OnlineApplicationInfo applicationInfo;

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

    public OnlineApplicationInfo getApplicationInfo() {
        return applicationInfo;
    }

    public void setApplicationInfo(OnlineApplicationInfo applicationInfo) {
        this.applicationInfo = applicationInfo;
    }
}
