package com.example.polls.payload;

import com.example.polls.util.ServiceError;
import com.example.polls.util.ServiceResponse;

import java.io.Serializable;

public class OnlineApplicationResponse extends ServiceResponse implements Serializable {
    private OnlineApplicationInfo applicationInfo;

    public OnlineApplicationResponse() {
        super();
    }

    public OnlineApplicationResponse(ServiceError error) {
        super(error);
    }
    public OnlineApplicationResponse(OnlineApplicationInfo applicationInfo) {
        super();
        this.applicationInfo = applicationInfo;
    }
    public OnlineApplicationInfo getApplicationInfo() {
        return applicationInfo;
    }

    public void setApplicationInfo(OnlineApplicationInfo applicationInfo) {
        this.applicationInfo = applicationInfo;
    }
}
