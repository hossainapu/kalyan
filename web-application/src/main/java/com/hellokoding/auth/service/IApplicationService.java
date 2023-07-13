package com.hellokoding.auth.service;

import com.hellokoding.auth.model.dto.ApplicationInfo;
import com.hellokoding.auth.model.dto.OnlineApplicationInfo;
import com.hellokoding.auth.model.dto.EnrolledApplication;
import com.hellokoding.auth.model.dto.EnrolledApplicationSearchCriteria;

import java.util.List;

public interface IApplicationService {

    OnlineApplicationInfo findByUuid(String uuid);
    boolean isValidUUID(String uuid);
    Object save(OnlineApplicationInfo applicationInfoEO);
    OnlineApplicationInfo findByContactAndDateOfBirth(String contactNumber, String dateOfBirth);
    OnlineApplicationInfo findById(Integer id);
    ApplicationInfo findByApplicationInfoId(Integer id);
    List<EnrolledApplication> searchEnrolledApplication(EnrolledApplicationSearchCriteria criteria);
    List<EnrolledApplication> searchUplodedApplication(EnrolledApplicationSearchCriteria criteria);
    Object updateEnrolledApplication(ApplicationInfo applicationInfo);
}
