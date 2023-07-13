package com.codetreatise.dto;

import java.io.Serializable;
import java.util.List;

public class ApplicationSearchResult implements Serializable {

    private List<ApplicationInfo> applicationInfos;
    private Integer totalCount;

    public List<ApplicationInfo> getApplicationInfos() {
        return applicationInfos;
    }

    public void setApplicationInfos(List<ApplicationInfo> applicationInfos) {
        this.applicationInfos = applicationInfos;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
