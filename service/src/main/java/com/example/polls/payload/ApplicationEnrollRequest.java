package com.example.polls.payload;

import java.io.Serializable;

public class ApplicationEnrollRequest implements Serializable {

    private ApplicationInfo applicationInfo;

    public ApplicationInfo getApplicationInfo() {
        return applicationInfo;
    }

    public void setApplicationInfo(ApplicationInfo applicationInfo) {
        this.applicationInfo = applicationInfo;
    }
}
