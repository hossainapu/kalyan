package com.codetreatise.dto;

import java.io.Serializable;

public class ApplicationEnrollRequest implements Serializable {

    private EnrollApplication applicationInfo;

    public EnrollApplication getApplicationInfo() {
        return applicationInfo;
    }

    public void setApplicationInfo(EnrollApplication applicationInfo) {
        this.applicationInfo = applicationInfo;
    }
}
