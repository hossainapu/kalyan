package com.example.polls.controller;

import com.example.polls.payload.*;
import com.example.polls.security.CurrentUser;
import com.example.polls.security.UserPrincipal;
import com.example.polls.service.IApplicationService;
import com.example.polls.util.ServiceError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@Controller
@RequestMapping("/api/application")
public class ApplicationController {

    @Autowired
    private IApplicationService service;

    @PostMapping("/enroll")
    public @ResponseBody ApplicationEnrollResponse enrollApplication(@Valid @RequestBody ApplicationEnrollRequest request,@CurrentUser UserPrincipal currentUser) {

        Object result = service.enrollApplication(request.getApplicationInfo(),currentUser);
        if(result instanceof ServiceError) {
            return new ApplicationEnrollResponse((ServiceError)result);
        }

        List<Object> list = (List<Object>) result;

        return new ApplicationEnrollResponse((Integer)list.get(0),(String)list.get(1));

    }

    @PostMapping("/find")
    public @ResponseBody GetApplicationResponse getApplication(@RequestBody GetApplicationRequest request,@CurrentUser UserPrincipal user) {

        Object object = service.getApplicationByReferenceNumber(request.getReferenceNumber(),request.getMobileNumber());
        if(object instanceof ServiceError) {
            return new GetApplicationResponse((ServiceError)object);
        }

        return new GetApplicationResponse((ApplicationInfo)object);
    }


    @GetMapping("/online-application/{uuid}")
    public @ResponseBody OnlineApplicationResponse getOnlineApplication(@PathVariable(value = "uuid") String uuid,@CurrentUser UserPrincipal userPrincipal) {
        Object object = service.getOnlineApplication(uuid);
        if(object instanceof ServiceError) {
            return new OnlineApplicationResponse((ServiceError)object);
        }

        return new OnlineApplicationResponse((OnlineApplicationInfo)object);
    }

    @PostMapping("/search-online-application")
    public @ResponseBody SearchApplicationResponse searchOnlineApplication(@RequestBody SearchApplicationRequest request) {
        Object object = service.searchOnlineApplication(request.getCriteria());
        if(object instanceof ServiceError) {
            return new SearchApplicationResponse((ServiceError)object);
        }

        return new SearchApplicationResponse((List<OnlineApplicationInfo>)object);
    }
}
