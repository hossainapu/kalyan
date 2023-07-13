package com.hossain.dto;

import java.io.Serializable;

public class GetApplicationResponse extends ServiceResponse implements Serializable {

    private ApplicationInfo applicationInfo;

    public ApplicationInfo getApplicationInfo() {
        return applicationInfo;
    }

    public void setApplicationInfo(ApplicationInfo applicationInfo) {
        this.applicationInfo = applicationInfo;
    }
}
