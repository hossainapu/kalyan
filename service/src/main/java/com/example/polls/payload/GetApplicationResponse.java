package com.example.polls.payload;

import com.example.polls.util.ServiceError;
import com.example.polls.util.ServiceResponse;

import java.io.Serializable;

public class GetApplicationResponse extends ServiceResponse implements Serializable {

    private ApplicationInfo applicationInfo;

    public GetApplicationResponse() {
        super();
    }

    public GetApplicationResponse(ServiceError error) {
        super(error);
    }

    public GetApplicationResponse(ApplicationInfo applicationInfo) {
        super();
        this.applicationInfo = applicationInfo;
    }

    public ApplicationInfo getApplicationInfo() {
        return applicationInfo;
    }

    public void setApplicationInfo(ApplicationInfo applicationInfo) {
        this.applicationInfo = applicationInfo;
    }
}
