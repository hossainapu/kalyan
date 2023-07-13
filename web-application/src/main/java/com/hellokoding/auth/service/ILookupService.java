package com.hellokoding.auth.service;

import com.hellokoding.auth.model.dto.District;
import com.hellokoding.auth.model.dto.Division;
import com.hellokoding.auth.model.dto.Thana;
import com.hellokoding.auth.model.dto.User;

import java.util.List;

public interface ILookupService {

    List<Division> getAllDivision();
    List<District> getAllDistrict();
    List<Thana> getAllThana();
    User getLoggedInUser();
    void sendSMS(String text,String mobileNumbers);
}
