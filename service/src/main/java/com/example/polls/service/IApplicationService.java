package com.example.polls.service;

import com.example.polls.payload.ApplicationInfo;
import com.example.polls.payload.OnlineApplicationSearchCriteria;
import com.example.polls.security.UserPrincipal;

public interface IApplicationService {

    Object enrollApplication(ApplicationInfo applicationInfo, UserPrincipal currentUser);
    Object getApplicationByReferenceNumber(String referenceNumber,String mobileNumber);
    Object getOnlineApplication(String uuid);
    Object searchOnlineApplication(OnlineApplicationSearchCriteria criteria);
}
