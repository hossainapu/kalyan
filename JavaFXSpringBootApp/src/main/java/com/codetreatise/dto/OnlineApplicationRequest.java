package com.codetreatise.dto;

import java.io.Serializable;

public class OnlineApplicationRequest implements Serializable {

    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
