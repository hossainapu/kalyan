package com.codetreatise.service;

import com.codetreatise.dto.ApplicationInfo;
import com.codetreatise.dto.ApplicationSearchCriteria;

import java.util.List;
import java.util.Map;

public interface IApplicationService {

    Object saveApplication(ApplicationInfo applicationInfo);
    Object updateApplication(ApplicationInfo applicationInfo);
    List<ApplicationInfo> searchApplication(ApplicationSearchCriteria criteria);
    Integer countApplication(ApplicationSearchCriteria criteria);
    ApplicationInfo findById(Integer id);

    int updateByNativeQuery(String sql, Map<String,Object> param);
}
